package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

/**
 * This app displays an order form to order coffee.
 */
public class JustJava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_java);
    }

    private static DecimalFormat df2 = new DecimalFormat(".##");

    int number = 0;
    int price = 5;
    String itemCount = "Item Count: ";
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
        number++;
        String quantity = itemCount + number;
        display(quantity);
        adjustPrice(price * number);
        TextView thankYouMessage = (TextView) findViewById(R.id.thank_you);
        thankYouMessage.setText("");
    }

    public void decrement (View view) {
        if (number == 0) {
            String quantity = itemCount + number;
            if (whippedCream() && chocolate()) {
                adjustPrice((price + 1 + 2) * number);
            } else if (whippedCream()) {
                adjustPrice((price + 1) * number)
            } else if (chocolate()) {
                adjustPrice((price + 2) * number)
            } else {
                adjustPrice(price * number);
            }
            display(quantity);
            TextView thankYouMessage = (TextView) findViewById(R.id.thank_you);
            thankYouMessage.setText("");
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        } else {
            number--;
            String quantity = itemCount + number;
            display(quantity);
            if (whippedCream() && chocolate()) {
                adjustPrice((price + 1 + 2) * number);
            } else if (whippedCream()) {
                adjustPrice((price + 1) * number)
            } else if (chocolate()) {
                adjustPrice((price + 2) * number)
            } else {
                adjustPrice(price * number);
            }
            TextView thankYouMessage = (TextView) findViewById(R.id.thank_you);
            thankYouMessage.setText("");
        }
    }
    public void submitOrder(View view) {
        TextView thankYouMessage = (TextView) findViewById(R.id.thank_you);
        if (number == 0) {
            thankYouMessage.setText("Quantity must be greater than 0 if you want to place an order");
        } else {
            if (name().length() == 0) {

                thankYouMessage.setText("Please enter a name...");
            } else {
                if (whippedCream() && chocolate()) {
                    adjustPrice((price + 1 + 2) * number);
                } else if (whippedCream()) {
                    adjustPrice((price + 1) * number)
                } else if (chocolate()) {
                    adjustPrice((price + 2) * number)
                } else {
                    adjustPrice(price * number);
                }

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_EMAIL, "mkmorfess@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "New Coffee Order");
                intent.putExtra(Intent.EXTRA_STREAM, name() +
                        "\nNumber of coffees: " + number +
                        "\nWhipped Cream? " + whippedCream() +
                        "\nChocolate? " + chocolate());
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

                thankYouMessage.setText("Thank you for your order, " + name()
                number = 0;
                String quantity = itemCount + number;
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
