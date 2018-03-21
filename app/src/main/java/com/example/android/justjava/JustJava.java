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
            adjustPrice(price * number);
            display(quantity);
            TextView thankYouMessage = (TextView) findViewById(R.id.thank_you);
            thankYouMessage.setText("");
        } else {
            number--;
            String quantity = itemCount + number;
            display(quantity);
            adjustPrice(price * number);
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

                adjustPrice(price * number);
                thankYouMessage.setText("Thank you for your order, " + name() +
                        "\nWhipped Cream? " + whippedCream() +
                        "\nChocolate? " + chocolate());
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
