package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import android.content.Intent;
import android.net.Uri;

/**
 * This app displays an order form to order coffee.
 */

public class JustJava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_java);

        thankYouMessage = (TextView) findViewById(R.id.thank_you);
    }

//    private static DecimalFormat df2 = new DecimalFormat(".##");

    int number = 0;
    int price = 5;
    String itemCount = "Item Count: ";
    public TextView thankYouMessage;

    /**
     * This method is called when the order button is clicked.
     */
    public boolean whippedCream () {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whippedCream);
        boolean whipCream = whippedCream.isChecked();
        return whipCream;
    }

    public boolean chocolate () {
        CheckBox chocolateBox = (CheckBox) findViewById(R.id.chocolate);
        boolean choco = chocolateBox.isChecked();
        return choco;
    }

    public String name()  {
        EditText newName = (EditText) findViewById(R.id.name);
        String theName = newName.getText().toString();
        return theName;
    }


    public void increment (View view) {
        thankYouMessage.setText("");
        if (number > 100) {
            String quantity = itemCount + number;
            display(quantity);

            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
        }
        else {
            number++;
            String quantity = itemCount + number;
            display(quantity);
            adjustPrice(price * number);
        }
    }

    public void decrement (View view) {
        thankYouMessage.setText("");
        if (number == 0) {
            String quantity = itemCount + number;
            display(quantity);

            Toast.makeText(this, "Please press the '+' to add coffees", Toast.LENGTH_SHORT).show();
        } else {
            number--;
            String quantity = itemCount + number;
            display(quantity);
            adjustPrice(price * number);
        }
    }
    public void submitOrder(View view) {
        String quantity = itemCount + number;
        if (number == 0) {

            Toast.makeText(this, "You cannot order 0 cups of coffee... ", Toast.LENGTH_SHORT).show();

        } else {

            if (name().length() == 0) {

                Toast.makeText(this, getString(R.string.name), Toast.LENGTH_SHORT).show();
            } else {
                int newCost;
                if (whippedCream() && chocolate()) {
                    adjustPrice((price + 1 + 2) * number);
                    newCost = (price + 1 + 2) * number;
                } else if (whippedCream()) {
                    adjustPrice((price + 1) * number);
                    newCost = (price + 1) * number;
                } else if (chocolate()) {
                    adjustPrice((price + 2) * number);
                    newCost = (price + 2) * number;
                } else {
                    adjustPrice(price * number);
                    newCost = price * number;
                }

                String priceMessage = "Total: $" + newCost +
                        "\nName: " + name() +
                        "\nNumber of coffees: " + number +
                        "\nWhipped Cream? " + whippedCream() +
                        "\nChocolate? " + chocolate();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, "mkmorfess@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Coffee Order");
                intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

                thankYouMessage.setText("Thank you for your order, " + name());
                number = 0;

                display(quantity);
            }
        }
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(String message) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(message);
    }

    private void adjustPrice(int price) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("$" + price);
    }
}
