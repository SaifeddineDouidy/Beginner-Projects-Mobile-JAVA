package com.example.tp_map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private double latitude;
    private double longitude;
    private double altitude;
    private float accuracy;

    private RequestQueue requestQueue;
    private static final String INSERT_URL = "http://192.168.216.79/tp_map_backend/createPosition.php";

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int PHONE_PERMISSION_REQUEST_CODE = 2;

    private static final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_PHONE_STATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        checkAndRequestPermissions();

        findViewById(R.id.button_show_map).setOnClickListener(v -> {
            if (latitude != 0 && longitude != 0) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Location not yet available", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkAndRequestPermissions() {
        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PHONE_PERMISSION_REQUEST_CODE);
        } else {
            getDeviceIdentifier();
            startLocationUpdates();
        }
    }

    private boolean hasRequiredPermissions() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void startLocationUpdates() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (locationManager == null || !hasRequiredPermissions()) {
            Toast.makeText(this, "Location services not available", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                updateLocationData(lastKnownLocation);
            }
        }

        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    60000,
                    150,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            updateLocationData(location);
                        }

                        @Override
                        public void onProviderEnabled(@NonNull String provider) {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.provider_enabled, provider),
                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onProviderDisabled(@NonNull String provider) {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.provider_disabled, provider),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        } catch (SecurityException e) {
            Toast.makeText(this, "Location permission error: " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void updateLocationData(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        altitude = location.getAltitude();
        accuracy = location.getAccuracy();

        @SuppressLint("StringFormatMatches") String msg = getString(R.string.new_location, latitude, longitude, altitude, accuracy);
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

        sendLocationToServer(latitude, longitude);
    }

    private void sendLocationToServer(final double lat, final double lon) {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                INSERT_URL,
                response -> Toast.makeText(getApplicationContext(),
                        "Position sent successfully!", Toast.LENGTH_SHORT).show(),
                error -> {
                    if (error.networkResponse != null) {
                        Log.d("ErrorSending_position", "Error code: " + error.networkResponse.statusCode);
                        Log.d("ErrorSending_position", "Response data: " + new String(error.networkResponse.data));
                    } else {
                        Log.d("ErrorSending_position", "Error: " + error.toString());
                    }
                    Toast.makeText(getApplicationContext(), "Error sending position: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        ) {
            @SuppressLint("HardwareIds")
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                String imei = getDeviceIdentifier();

                params.put("latitude", String.valueOf(lat));
                params.put("longitude", String.valueOf(lon));
                params.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                params.put("imei", imei);

                return params;
            }
        };
        requestQueue.add(request);
    }

    @SuppressLint("HardwareIds")
    private String getDeviceIdentifier() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = "null";

        try {
            if (telephonyManager != null && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    imei = telephonyManager.getImei();
                } else {
                    imei = telephonyManager.getDeviceId();
                }
            }
        } catch (SecurityException e) {
            Log.e("IMEIError", "Permission not granted for reading IMEI", e);
            imei = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        }

        return imei;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PHONE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && allPermissionsGranted(grantResults)) {
                getDeviceIdentifier();
                startLocationUpdates();
            } else {
                Toast.makeText(this, "Required permissions were denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean allPermissionsGranted(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
