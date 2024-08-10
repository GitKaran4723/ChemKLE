package com.example.mysolutions;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.graphics.Typeface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private AutoCompleteTextView autoCompleteTextViewCompound;
    private Spinner spinnerMeasurementType;
    private EditText editTextAmountSolution;
    private EditText editTextMeasurementValue;
    private Button buttonCalculate;
    private TextView textViewResult;
    private ImageButton buttonShowCompounds;

    private List<Compound> compounds;
    private CustomArrayAdapter compoundAdapter;
    private ArrayAdapter<String> measurementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextViewCompound = findViewById(R.id.autoCompleteTextViewCompound);
        spinnerMeasurementType = findViewById(R.id.spinnerMeasurementType);
        editTextAmountSolution = findViewById(R.id.editTextAmountSolution);
        editTextMeasurementValue = findViewById(R.id.editTextMeasurementValue);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        buttonShowCompounds = findViewById(R.id.infoButton);

        initializeCompounds();
        setupAutoCompleteTextView();
        setupMeasurementSpinner();
        setupCalculateButton();
        setupShowCompoundsButton();
    }

    private void setupShowCompoundsButton() {
        buttonShowCompounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCompoundsPopup();
            }
        });
    }

    private void showCompoundsPopup() {
        // Create and configure the dialog
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_window);
        dialog.setTitle("Available Compounds");

        // Find views in the dialog layout
        ListView listViewCompounds = dialog.findViewById(R.id.listViewCompounds);

        // Prepare the list of compound names
        List<String> compoundNames = new ArrayList<>();
        for (Compound compound : compounds) {
            compoundNames.add(compound.getName());
        }

        // Create and set the adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, compoundNames);
        listViewCompounds.setAdapter(adapter);

        // Handle item clicks in the ListView
        listViewCompounds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected compound name
                String selectedCompound = (String) parent.getItemAtPosition(position);

                // Set the selected compound name to the AutoCompleteTextView
                autoCompleteTextViewCompound.setText(selectedCompound);

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();
    }


    private void initializeCompounds() {
        compounds = new ArrayList<>();

        compounds.add(new Compound("Ammonium Acetate (CH₃COONH₄)", 77.8000, 77.8000));
        compounds.add(new Compound("Aluminium Chloride (Anhydrous) (AlCl₃)", 133.3400, 44.4400));
        compounds.add(new Compound("Aluminium Chloride Hexahydrate (AlCl₃.6H₂O)", 241.4300, 80.4700));
        compounds.add(new Compound("Aluminium Hydroxide (Al(OH)₃)", 78.0040, 26.0000));
        compounds.add(new Compound("Aluminium Sulphate (Al₂(SO₄)₃)", 341.9600, 57.0000));
        compounds.add(new Compound("Ammonium Bicarbonate (NH₄HCO₃)", 79.0560, 79.0560));
        compounds.add(new Compound("Ammonium Carbonate ((NH₄)₂CO₃)", 96.0000, 48.0550));
        compounds.add(new Compound("Ammonium Chloride (NH₄Cl)", 53.4910, 53.4910));
        compounds.add(new Compound("Ammonium Chromate ((NH₄)₂CrO₄)", 152.0700, 76.0000));
        compounds.add(new Compound("Ammonium Molybdate ((NH₄)₂MoO₄)", 196.0400, 98.0200));
        compounds.add(new Compound("Ammonium Nitrate (NH₄NO₃)", 80.0430, 80.0430));
        compounds.add(new Compound("Ammonium Oxalate ((NH₄)₂C₂O₄)", 124.1000, 62.0000));
        compounds.add(new Compound("Ammonium Sulfate ((NH₄)₂SO₄)", 132.1400, 132.1400));
        compounds.add(new Compound("Ammonium Iodide (NH₄I)", 144.9400, 144.9400));
        compounds.add(new Compound("Arsenic Trioxide (As₂O₃)", 197.8400, 65.9470));
        compounds.add(new Compound("Sodium Bicarbonate (NaHCO₃)", 84.0066, 84.0066));
        compounds.add(new Compound("Barium Carbonate (BaCO₃)", 197.3400, 98.6500));
        compounds.add(new Compound("Barium Chloride (BaCl₂)", 208.2300, 104.1000));
        compounds.add(new Compound("Barium Chloride Dihydrate (BaCl₂.2H₂O)", 244.2800, 122.1500));
        compounds.add(new Compound("Barium Nitrate (Ba(NO₃)₂)", 261.3400, 130.6700));
        compounds.add(new Compound("Barium Sulfate (BaSO₄)", 233.3800, 116.7000));
        compounds.add(new Compound("Barium Sulfite (BaSO₃)", 217.3900, 108.7000));
        compounds.add(new Compound("Calcium Carbonate (CaCO₃)", 100.0869, 50.0500));
        compounds.add(new Compound("Calcium Chloride (CaCl₂)", 110.9800, 55.4500));
        compounds.add(new Compound("Calcium Chloride Hexahydrate (CaCl₂.6H₂O)", 219.0700, 109.5500));
        compounds.add(new Compound("Calcium Hydroxide (Ca(OH)₂)", 74.0900, 37.0500));
        compounds.add(new Compound("Calcium Sulphate (CaSO₄)", 136.1400, 68.1000));
        compounds.add(new Compound("Calcium Sulphate Dihydrate (CaSO₄.2H₂O)", 172.1700, 86.0850));
        compounds.add(new Compound("Cobalt(II) Chloride (CoCl₂)", 129.8390, 64.9000));
        compounds.add(new Compound("Cobalt(II) Chloride Hexahydrate (CoCl₂.6H₂O)", 237.9000, 118.9500));
        compounds.add(new Compound("Cobalt(II) Hydroxide (Co(OH)₂)", 92.9480, 46.4500));
        compounds.add(new Compound("Copper(I) Bromide (CuBr)", 143.4500, 143.4500));
        compounds.add(new Compound("Copper(I) Chloride (CuCl)", 132.8670, 132.8670));
        compounds.add(new Compound("Copper(I) Sulfide (Cu₂S)", 159.1600, 79.5500));
        compounds.add(new Compound("Copper(II) Chloride (CuCl₂)", 134.4500, 67.2000));
        compounds.add(new Compound("Copper(II) Chloride Dihydrate (CuCl₂.2H₂O)", 170.4800, 85.2500));
        compounds.add(new Compound("Copper(II) Hydroxide (Cu(OH)₂)", 97.5610, 48.8000));
        compounds.add(new Compound("Copper(II) Sulfate (CuSO₄)", 160.0000, 79.8040));
        compounds.add(new Compound("Copper(II) Sulfate Pentahydrate (CuSO₄.5H₂O)", 249.6900, 124.5000));
        compounds.add(new Compound("Disodium Tetraborate (Na₂B₄O₇.10H₂O)", 381.3700, 190.6850));
        compounds.add(new Compound("Ferric Chloride (FeCl₃)", 162.2000, 54.0700));
        compounds.add(new Compound("Iron(III) Chloride Hexahydrate (FeCl₃.6H₂O)", 270.2900, 90.1000));
        compounds.add(new Compound("Ferrous Ammonium Sulfate ((NH₄)₂Fe(SO₄)₂.6H₂O)", 392.1400, 392.1400));
        compounds.add(new Compound("Ferrous Sulfate (FeSO₄)", 151.9100, 151.9100));
        compounds.add(new Compound("Ferrous Sulfate Heptahydrate (FeSO₄.7H₂O)", 278.0200, 278.0200));
        compounds.add(new Compound("Gold Chloride (AuCl)", 232.4200, 232.4200));
        compounds.add(new Compound("Gold Cyanide (AuCN)", 222.9850, 222.9800));
        compounds.add(new Compound("Iron(II) Chloride Dihydrate (FeCl₂.2H₂O)", 198.8000, 99.4000));
        compounds.add(new Compound("Iron(II) Chloride Tetrahydrate (FeCl₂.4H₂O)", 234.9000, 117.4500));
        compounds.add(new Compound("Lead(II) Acetate (Pb(C₂H₃O₂)₂)", 325.3000, 162.6500));
        compounds.add(new Compound("Lead Dioxide (PbO₂)", 239.2000, 119.6000));
        compounds.add(new Compound("Lead(II) Carbonate (PbCO₃)", 267.2089, 133.6000));
        compounds.add(new Compound("Lead(II) Chloride (PbCl₂)", 278.0000, 139.0500));
        compounds.add(new Compound("Magnesite (MgCO₃)", 84.3139, 42.1500));
        compounds.add(new Compound("Magnesium Nitrate (Mg(NO₃)₂)", 148.3000, 74.1500));
        compounds.add(new Compound("Magnesium Nitrate Hexahydrate (Mg(NO₃)₂.6H₂O)", 256.4065, 128.2000));
        compounds.add(new Compound("Magnesium Sulfate (MgSO₄)", 120.3660, 60.2000));
        compounds.add(new Compound("Magnesium Sulfate Heptahydrate (MgSO₄.7H₂O)", 246.4700, 123.2500));
        compounds.add(new Compound("Manganese(II) Chloride (MnCl₂)", 125.8400, 62.9000));
        compounds.add(new Compound("Manganese(II) Chloride Tetrahydrate (MnCl₂.4H₂O)", 197.9000, 98.9500));
        compounds.add(new Compound("Manganese(II) Nitrate (Mn(NO₃)₂)", 178.9500, 89.4500));
        compounds.add(new Compound("Manganese(II) Nitrate Tetrahydrate (Mn(NO₃)₂.4H₂O)", 251.0100, 125.9500));
        compounds.add(new Compound("Manganese(II) Sulfate (MnSO₄)", 150.9900, 75.4000));
        compounds.add(new Compound("Manganese(II) Sulfate Monohydrate (MnSO₄.H₂O)", 169.0100, 84.5100));
        compounds.add(new Compound("Mercuric Chloride (HgCl₂)", 271.5000, 135.7500));
        compounds.add(new Compound("Mercuric Oxide (HgO)", 216.5900, 108.3000));
        compounds.add(new Compound("Mercuric Sulfate (HgSO₄)", 296.7000, 148.3500));
        compounds.add(new Compound("Mercurous Chloride (Hg₂Cl₂)", 236.0600, 236.0600));
        compounds.add(new Compound("Nickel(II) Chloride (NiCl₂)", 129.5994, 64.8000));
        compounds.add(new Compound("Nickel(II) Chloride Hexahydrate (NiCl₂.6H₂O)", 237.6900, 118.8500));
        compounds.add(new Compound("Nickel(II) Nitrate (Ni(NO₃)₂)", 182.7000, 91.3500));
        compounds.add(new Compound("Nickel(II) Nitrate Hexahydrate (Ni(NO₃)₂.6H₂O)", 290.7900, 145.4000));
        compounds.add(new Compound("Nickel(II) Sulfate (NiSO₄)", 154.7600, 77.4000));
        compounds.add(new Compound("Nickel(II) Sulfate Hexahydrate (NiSO₄.6H₂O)", 262.8500, 131.4500));
        compounds.add(new Compound("Potassium Bromate (KBrO₃)", 167.0000, 83.4400));
        compounds.add(new Compound("Potassium Bromide (KBr)", 119.0000, 119.0000));
        compounds.add(new Compound("Potassium Carbonate (K₂CO₃)", 138.2100, 69.0000));
        compounds.add(new Compound("Potassium Chloride (KCl)", 74.5513, 74.5513));
        compounds.add(new Compound("Potassium Chromate (K₂CrO₄)", 194.1880, 97.0000));
        compounds.add(new Compound("Potassium Cyanide (KCN)", 65.0000, 65.0000));
        compounds.add(new Compound("Potassium Ferricyanide (K₃Fe(CN)₆)", 329.2500, 164.6200));
        compounds.add(new Compound("Potassium Ferrocyanide (K₄Fe(CN)₆.3H₂O)", 368.4000, 184.2000));
        compounds.add(new Compound("Potassium Fluoride (KF)", 58.0967, 58.0967));
        compounds.add(new Compound("Potassium Iodate (KIO₃)", 214.0000, 214.0000));
        compounds.add(new Compound("Potassium Iodide (KI)", 166.0000, 166.0000));
        compounds.add(new Compound("Potassium Nitrate (KNO₃)", 101.1032, 101.1032));
        compounds.add(new Compound("Potassium Perchlorate (KClO₄)", 138.5540, 138.5540));
        compounds.add(new Compound("Potassium Permanganate (KMnO₄)", 158.0340, 158.0340));
        compounds.add(new Compound("Potassium Phosphate (KH₂PO₄)", 136.0860, 136.0860));
        compounds.add(new Compound("Sodium Chloride (NaCl)", 58.4430, 58.4430));
        compounds.add(new Compound("Silver Acetate (CH₃COOAg)", 166.9100, 166.9100));
        compounds.add(new Compound("Silver Carbonate (Ag₂CO₃)", 275.7494, 275.7494));
        compounds.add(new Compound("Silver Chloride (AgCl)", 143.3210, 143.3210));
        compounds.add(new Compound("Silver Nitrate (AgNO₃)", 169.8730, 169.8730));
        compounds.add(new Compound("Silver Oxide (Ag₂O)", 231.7350, 231.7350));
        compounds.add(new Compound("Sodium Bromate (NaBrO₃)", 150.8900, 150.8900));
        compounds.add(new Compound("Sodium Carbonate (Na₂CO₃)", 105.9888, 105.9888));
        compounds.add(new Compound("Sodium Fluoride (NaF)", 41.9882, 41.9882));
        compounds.add(new Compound("Sodium Hypochlorite (NaClO)", 74.4420, 74.4420));
        compounds.add(new Compound("Sodium Iodide (NaI)", 149.8940, 149.8940));
        compounds.add(new Compound("Sodium Nitrite (NaNO₂)", 68.9953, 68.9953));
        compounds.add(new Compound("Sodium Oxalate (Na₂C₂O₄)", 134.0000, 134.0000));
        compounds.add(new Compound("Sodium Perchlorate (NaClO₄)", 122.4400, 122.4400));
        compounds.add(new Compound("Sodium Phosphate (Na₃PO₄)", 163.9407, 163.9407));
        compounds.add(new Compound("Sodium Sulfate (Na₂SO₄)", 142.0400, 142.0400));
        compounds.add(new Compound("Sodium Sulfite (Na₂SO₃)", 126.0400, 126.0400));
        compounds.add(new Compound("Sodium Thiosulfate (Na₂S₂O₃)", 158.1100, 158.1100));
        compounds.add(new Compound("Strontium Carbonate (SrCO₃)", 147.6300, 73.8000));
        compounds.add(new Compound("Strontium Chloride (SrCl₂)", 158.5300, 79.2700));
        compounds.add(new Compound("Strontium Nitrate (Sr(NO₃)₂)", 211.6300, 105.8100));
        compounds.add(new Compound("Zinc Carbonate (ZnCO₃)", 125.3800, 125.3800));
        compounds.add(new Compound("Zinc Chloride (ZnCl₂)", 136.2860, 68.1440));
        compounds.add(new Compound("Zinc Oxide (ZnO)", 81.3800, 81.3800));
        compounds.add(new Compound("Zinc Phosphate (Zn₃(PO₄)₂)", 386.1100, 386.1100));
        compounds.add(new Compound("Zinc Sulfate (ZnSO₄)", 161.4500, 80.7300));
        compounds.add(new Compound("Zinc Sulfide (ZnS)", 97.4600, 97.4600));
        compounds.add(new Compound("Ethylenediaminetetraacetic Acid (C₁₀H₁₆N₂O₈)", 97.4600, 97.4600));
        // Add more compounds as needed

        compoundAdapter = new CustomArrayAdapter(this, compounds);
        autoCompleteTextViewCompound.setAdapter(compoundAdapter);
    }

    private void setupAutoCompleteTextView() {
        autoCompleteTextViewCompound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Compound selectedCompound = (Compound) parent.getItemAtPosition(position);
                if (selectedCompound != null) {
                    autoCompleteTextViewCompound.setText(selectedCompound.getName());
                }
            }
        });
    }

    private void setupMeasurementSpinner() {
        List<String> measurementTypes = new ArrayList<>();
        measurementTypes.add("Normality");
        measurementTypes.add("Molarity");
        measurementAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, measurementTypes);
        measurementAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMeasurementType.setAdapter(measurementAdapter);
    }

    private void setupCalculateButton() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAmount();
            }
        });
    }

    private void calculateAmount() {
        // Get selected compound
        String compoundName = autoCompleteTextViewCompound.getText().toString();

        Compound selectedCompound = null;
        for (Compound compound : compounds) {
            if (compound.getName().equalsIgnoreCase(compoundName)) {
                selectedCompound = compound;
                break;
            }
        }
        if (selectedCompound == null) {
            Toast.makeText(this, "Please select a valid compound", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if amount solution is empty
        String amountSolutionText = editTextAmountSolution.getText().toString();
        if (amountSolutionText.isEmpty()) {
            Toast.makeText(this, "Please enter the amount of solution in ml", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if measurement value is empty
        String measurementValueText = editTextMeasurementValue.getText().toString();
        if (measurementValueText.isEmpty()) {
            Toast.makeText(this, "Please enter the value", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse input values and handle potential parsing errors
        double molecularMass = selectedCompound.getMolecularMass();
        double equivalentMass = selectedCompound.getEquivalentMass();
        double amountSolution;
        double measurementValue;
        try {
            amountSolution = Double.parseDouble(amountSolutionText);
            measurementValue = Double.parseDouble(measurementValueText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate the amount required based on Normality or Molarity
        String selectedMeasurement = (String) spinnerMeasurementType.getSelectedItem();
        double amountRequired;
        if (selectedMeasurement.equals("Normality")) {
            amountRequired = measurementValue * equivalentMass * amountSolution / 1000 ;
        } else {
            amountRequired = measurementValue * molecularMass * amountSolution / 1000 ;
        }

        // Format the result and apply styles
        String resultText = String.format("Amount of %s required: %.3f grams", selectedCompound.getName(), amountRequired);

        // Find the index of the grams value in the string
        int startIndex = resultText.indexOf(String.format("%.3f", amountRequired));
        int endIndex = startIndex + String.format("%.3f", amountRequired).length();

        // Create a SpannableString to apply styles
        SpannableString spannableString = new SpannableString(resultText);

        // Apply larger text size to the grams value
        spannableString.setSpan(new RelativeSizeSpan(1.5f), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Apply bold style to the grams value
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the styled text to the TextView
        textViewResult.setText(spannableString);
    }
}
