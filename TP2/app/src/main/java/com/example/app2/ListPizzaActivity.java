package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.app2.Adapter.FruitAdapter;
import com.example.app2.Adapter.PizzaAdapter;
import com.example.app2.classes.Produit;
import com.example.app2.services.ProduitService;

public class ListPizzaActivity extends AppCompatActivity {
    private ProduitService produitService;
    private ListView list;
    private ImageView shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_pizza);
        produitService = new ProduitService();

        produitService.create(new Produit(
                "BARBECUED CHICKEN PIZZA",
                3,
                R.mipmap.pizza1,
                35.0f,
                "- 2 boneless skinless chicken breast halves (6 ounces each)\n"
                        + "- 1/4 teaspoon pepper\n"
                        + "- 1 cup barbecue sauce, divided\n"
                        + "- 1 tube (13.8 ounces) refrigerated pizza crust\n"
                        + "- 2 teaspoons olive oil\n"
                        + "- 2 cups shredded Gouda cheese\n"
                        + "- 1 small red onion, halved and thinly sliced\n"
                        + "- 1/4 cup minced fresh cilantro",
                "So fast and so easy with refrigerated pizza crust, these saucy, smoky pizzas make quick fans "
                        + "with their hot-off-the-grill, rustic flavor. They're perfect for spur-of-the-moment cookouts "
                        + "and summer dinners on the patio. —Alicia Trevithick, Temecula, California",
                "STEP 1:\n\n"
                        + "  Sprinkle chicken with pepper; place on an oiled grill rack over medium heat. Grill, "
                        + "covered, until a thermometer reads 165°, 5-7 minutes per side, basting frequently with "
                        + "1/2 cup barbecue sauce during the last 4 minutes. Cool slightly. Cut into cubes.\n\n"
                        + "STEP 2:\n\n"
                        + "  Divide dough in half. On a well-greased large sheet of heavy-duty foil, press each portion "
                        + "of dough into a 10x8-in. rectangle; brush lightly with oil. Invert dough onto grill rack; "
                        + "peel off foil. Grill, covered, over medium heat until the bottom is lightly browned, "
                        + "1-2 minutes.\n\n"
                        + "STEP 3:\n\n"
                        + "  Remove from grill. Spread grilled sides with remaining barbecue sauce. Top with cheese, "
                        + "chicken, and onion. Grill, covered, until the bottom is lightly browned and cheese is melted, "
                        + "2-3 minutes. Sprinkle with cilantro. Yield: 2 pizzas (4 pieces each)."
        ));
        produitService.create(new Produit(
                "BRUSCHETTA PIZZA",
                5,
                R.mipmap.pizza2,
                35.0f,
                "- 1/2 pound reduced-fat bulk pork sausage\n" +
                        "- 1 prebaked 12-inch pizza crust\n" +
                        "- 1 package (6 ounces) sliced turkey pepperoni\n" +
                        "- 2 cups shredded part-skim mozzarella cheese\n" +
                        "- 1-1/2 cups chopped plum tomatoes\n" +
                        "- 1/2 cup fresh basil leaves, thinly sliced\n" +
                        "- 1 tablespoon olive oil\n" +
                        "- 2 garlic cloves, minced\n" +
                        "- 1/2 teaspoon minced fresh thyme or 1/8 teaspoon dried thyme\n" +
                        "- 1/2 teaspoon balsamic vinegar\n" +
                        "- 1/4 teaspoon salt\n" +
                        "- 1/8 teaspoon pepper\n" +
                        "- Additional fresh basil leaves, optional",
                "You might need a knife and fork for this hearty pizza! " +
                        "Loaded with Italian flavor and plenty of fresh tomatoes, it's bound to become a family favorite. " +
                        "It's even better with a homemade, whole wheat crust! —Debra Kell, Owasso, Oklahoma",
                "STEP 1:\n\n" +
                        "In a small skillet, cook sausage over medium heat until no longer pink; drain. " +
                        "Place crust on an ungreased baking sheet. Top with pepperoni, sausage, and cheese. " +
                        "Bake at 450° for 10-12 minutes or until cheese is melted.\n\n" +
                        "STEP 2:\n\n" +
                        "In a small bowl, combine the tomatoes, sliced basil, oil, garlic, thyme, vinegar, salt, and pepper. " +
                        "Spoon over pizza. Garnish with additional basil if desired. Yield: 8 slices."
        ));

        produitService.create(new Produit(
                "SPINACH PIZZA",
                2,
                R.mipmap.pizza3,
                25.0f,
                "- 1 package (6-1/2 ounces) pizza crust mix\n"
                        + "- 1/2 cup Alfredo sauce\n"
                        + "- 2 medium tomatoes\n"
                        + "- 4 cups chopped fresh spinach\n"
                        + "- 2 cups shredded Italian cheese blend",
                "This tasty pizza is so easy to prepare. My family, including my young daughter, loves it. "
                        + "What an easy way to make a delicious, veggie-filled meal! —Dawn Bartholomew, Raleigh, North Carolina",
                "STEP 1:\n\n"
                        + "Prepare pizza dough according to package directions. With floured hands, press dough onto a greased 12-in. pizza pan.\n\n"
                        + "STEP 2:\n\n"
                        + "Spread Alfredo sauce over dough to within 1 in. of edges. Thinly slice or chop tomatoes; top pizza with spinach, tomatoes, and cheese.\n\n"
                        + "STEP 3:\n\n"
                        + "Bake at 450° for 10-15 minutes or until cheese is melted and crust is golden brown. Yield: 4-6 servings."
        ));

        list = findViewById(R.id.pizzaListe);
        list.setAdapter(new PizzaAdapter(produitService.findAll(), this));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListPizzaActivity.this, PizzaDescriptionActivity.class);
                intent.putExtra("pizzaName", produitService.findAll().get(position).getNom());
                intent.putExtra("pizzaImage", produitService.findAll().get(position).getImage());
                intent.putExtra("pizzaDescription", produitService.findAll().get(position).getDescription());
                intent.putExtra("pizzaIngredients", produitService.findAll().get(position).getDetailsIngredients());
                intent.putExtra("pizzaPreparation", produitService.findAll().get(position).getPreparation());
                startActivity(intent);
            }
        });

        shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("THE IMAGE  WAS  CLICKED");
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Testing");
                try {
                    whatsappIntent.setPackage("com.whatsapp");
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    System.out.println("Whatsapp isnt installed");
                }
            }
        });

    }
}