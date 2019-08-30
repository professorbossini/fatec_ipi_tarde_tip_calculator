package br.com.bossini.fatec_ipi_tarde_tip_calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat =
            NumberFormat.getPercentInstance();
    private double billAmount = 1000.0;
    private double percent = 0.15;

    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountTextView = findViewById(R.id.amountTextView);
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);
        SeekBar percentSeekBar = findViewById(R.id.percentSeekbar);
        percentSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        EditText amountEditText = findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountTextWatcher);
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    percent = i / 100D;
                    double tip = billAmount * percent;
                    double total = billAmount + tip;
                    tipTextView.setText(currencyFormat.format(tip));
                    totalTextView.setText(currencyFormat.format(total));
                    percentTextView.setText(percentFormat.format(percent));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
    TextWatcher amountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try{
                billAmount = Double.parseDouble(charSequence.toString()) / 100;
                double tip = percent * billAmount;
                double total = tip + billAmount;
                tipTextView.setText(currencyFormat.format(tip));
                totalTextView.setText(currencyFormat.format(total));
                amountTextView.setText(currencyFormat.format(billAmount));
            }
            catch (NumberFormatException e){
                tipTextView.setText(currencyFormat.format(0d));
                totalTextView.setText(currencyFormat.format(0d));
                amountTextView.setText(currencyFormat.format(0d));
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
