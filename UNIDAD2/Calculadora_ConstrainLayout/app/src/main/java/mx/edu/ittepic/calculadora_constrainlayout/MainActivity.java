package mx.edu.ittepic.calculadora_constrainlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Double numero1, numero2, resultado;
    String operador;
    boolean coma = false;
    TextView valor;
    TextView superior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        this.valor = (TextView) findViewById(R.id.pprincipal);
        this.superior = (TextView) findViewById(R.id.psuperior);
        Button b0 = (Button) findViewById(R.id.b0);
        Button b1 = (Button) findViewById(R.id.b1);
        Button b2 = (Button) findViewById(R.id.b2);
        Button b3 = (Button) findViewById(R.id.b3);
        Button b4 = (Button) findViewById(R.id.b4);
        Button b5 = (Button) findViewById(R.id.b5);
        Button b6 = (Button) findViewById(R.id.b6);
        Button b7 = (Button) findViewById(R.id.b7);
        Button b8 = (Button) findViewById(R.id.b8);
        Button b9 = (Button) findViewById(R.id.b9);
        Button bborrar = (Button) findViewById(R.id.bborrar);
        Button braiz = (Button) findViewById(R.id.braiz);
        Button bpotencia = (Button) findViewById(R.id.bpotencia);
        Button bdivide = (Button) findViewById(R.id.bdivide);
        Button bsuma = (Button) findViewById(R.id.bsuma);
        Button bresta = (Button) findViewById(R.id.bresta);
        Button bmulti = (Button) findViewById(R.id.bmulti);
        Button bcoma = (Button) findViewById(R.id.bcoma);
        Button bsigno = (Button) findViewById(R.id.bsigno);
        Button bresultado = (Button) findViewById(R.id.bresultado);

        bcoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coma == false) {
                    if (valor.getText().equals("0")) {
                        valor.setText("0.");
                    } else {
                        valor.setText(valor.getText() + ".");
                    }
                    coma = true;
                }
            }
        });

        bsigno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero1 = Double.parseDouble(valor.getText().toString());
                numero1 = numero1 * (-1);
                valor.setText(formatear(numero1));
            }
        });

        bpotencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero1 = Double.parseDouble(valor.getText().toString());
                numero1 = Math.pow(numero1, 2);
                valor.setText(numero1.toString());
                coma = false;
            }
        });

        braiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero1 = Double.parseDouble(valor.getText().toString());
                numero1 = Math.sqrt(numero1);
                valor.setText(numero1.toString());
                coma = false;
            }
        });

        bsuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operador = "+";
                numero1 = Double.parseDouble(valor.getText().toString());
                superior.setText(formatear(numero1) + " +");
                valor.setText("0");
                coma = false;
            }
        });

        bresta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operador = "-";
                numero1 = Double.parseDouble(valor.getText().toString());
                superior.setText(formatear(numero1) + " -");
                valor.setText("0");
                coma = false;
            }
        });

        bmulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operador = "*";
                numero1 = Double.parseDouble(valor.getText().toString());
                superior.setText(formatear(numero1) + " *");
                valor.setText("0");
                coma = false;
            }
        });

        bdivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operador = "/";
                numero1 = Double.parseDouble(valor.getText().toString());
                superior.setText(formatear(numero1) + " /");
                valor.setText("0");
                coma = false;
            }
        });

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valor.getText().equals("0")) {
                    valor.setText("0");
                } else {
                    valor.setText(valor.getText() + "0");
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valor.getText().equals("0")) {
                    valor.setText("1");
                } else {
                    valor.setText(valor.getText() + "1");
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valor.getText().equals("0")) {
                    valor.setText("2");
                } else {
                    valor.setText(valor.getText() + "2");
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valor.getText().equals("0")) {
                    valor.setText("3");
                } else {
                    valor.setText(valor.getText() + "3");
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valor.getText().equals("0")) {
                    valor.setText("4");
                } else {
                    valor.setText(valor.getText() + "4");
                }
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valor.getText().equals("0")) {
                    valor.setText("5");
                } else {
                    valor.setText(valor.getText() + "5");
                }
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valor.getText().equals("0")) {
                    valor.setText("6");
                } else {
                    valor.setText(valor.getText() + "6");
                }
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valor.getText().equals("0")) {
                    valor.setText("7");
                } else {
                    valor.setText(valor.getText() + "8");
                }
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valor.getText().equals("0")) {
                    valor.setText("8");
                } else {
                    valor.setText(valor.getText() + "8");
                }
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valor.getText().equals("0")) {
                    valor.setText("9");
                } else {
                    valor.setText(valor.getText() + "9");
                }
            }
        });

        bborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero1 = 0.0;
                numero2 = 0.0;
                resultado = 0.0;
                operador = "";
                valor.setText("0");
                superior.setText("");
                coma = false;
            }
        });

        bresultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero2 = Double.parseDouble(valor.getText().toString());
                if (operador.equals("+")) {
                    resultado = numero1 + numero2;
                    superior.setText(formatear(numero1) + " + " + formatear(numero2));
                } else if (operador.equals("-")) {
                    resultado = numero1 - numero2;
                    superior.setText(formatear(numero1) + " - " + formatear(numero2));
                } else if (operador.equals("*")) {
                    resultado = numero1 * numero2;
                    superior.setText(formatear(numero1) + " * " + formatear(numero2));
                } else if (operador.equals("/")) {
                    resultado = numero1 / numero2;
                    superior.setText(formatear(numero1) + " / " + formatear(numero2));
                }
                valor.setText(formatear(resultado));

                if (formatear(resultado).indexOf(".") > -1) {
                    coma = true;
                } else {
                    coma = false;
                }
            }
        });
    }

    public String formatear(Double numero) {
        if (numero % 1 == 0) {
            int var = numero.intValue();
            return String.valueOf(var);
        }
        else
        {
            return String.valueOf(numero);
        }
    }

}
