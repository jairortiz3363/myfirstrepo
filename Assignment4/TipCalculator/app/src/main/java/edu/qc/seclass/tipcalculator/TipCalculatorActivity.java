package edu.qc.seclass.tipcalculator;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class TipCalculatorActivity extends AppCompatActivity {
    // Variables for the UI elements
    private EditText checkAmountValue, partySizeValue;
    private TextView fifteenPercentTipValue, twentyPercentTipValue, twentyfivePercentTipValue;
    private TextView fifteenPercentTotalValue, twentyPercentTotalValue, twentyfivePercentTotalValue;
    private Button computeTipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tip_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        checkAmountValue = findViewById(R.id.checkAmountValue);
        partySizeValue = findViewById(R.id.partySizeValue);
        fifteenPercentTipValue = findViewById(R.id.fifteenPercentTipValue);
        twentyPercentTipValue = findViewById(R.id.twentyPercentTipValue);
        twentyfivePercentTipValue = findViewById(R.id.twentyfivePercentTipValue);
        fifteenPercentTotalValue = findViewById(R.id.fifteenPercentTotalValue);
        twentyPercentTotalValue = findViewById(R.id.twentyPercentTotalValue);
        twentyfivePercentTotalValue = findViewById(R.id.twentyfivePercentTotalValue);
        computeTipButton = findViewById(R.id.computeTip);
        // Tip Button
        computeTipButton.setOnClickListener(v -> {
            try {
                // Get input values from the user
                double checkAmount = Double.parseDouble(checkAmountValue.getText().toString());
                int partySize = Integer.parseInt(partySizeValue.getText().toString());

                if (partySize == 0) {
                    // Show an error message if party size is zero
                    Toast.makeText(this, "Party size cannot be zero", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calculate tips
                long fifteenTip = Math.round((checkAmount * 0.15) / partySize);
                long twentyTip = Math.round((checkAmount * 0.20) / partySize);
                long twentyfiveTip = Math.round((checkAmount * 0.25) / partySize);

                // Calculate totals
                long fifteenTotal = Math.round(checkAmount / partySize + fifteenTip);
                long twentyTotal = Math.round(checkAmount / partySize + twentyTip);
                long twentyfiveTotal = Math.round(checkAmount / partySize + twentyfiveTip);

                // Display results
                fifteenPercentTipValue.setText(String.valueOf(fifteenTip));
                twentyPercentTipValue.setText(String.valueOf(twentyTip));
                twentyfivePercentTipValue.setText(String.valueOf(twentyfiveTip));
                fifteenPercentTotalValue.setText(String.valueOf(fifteenTotal));
                twentyPercentTotalValue.setText(String.valueOf(twentyTotal));
                twentyfivePercentTotalValue.setText(String.valueOf(twentyfiveTotal));

            } catch (NumberFormatException e) {
                // Handle invalid input
                Toast.makeText(this, "Empty or incorrect value(s)!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}