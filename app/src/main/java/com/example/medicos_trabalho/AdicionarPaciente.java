package com.example.medicos_trabalho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdicionarPaciente extends AppCompatActivity {
    private TextView novoPaciente;
    private TextView nome;
    private TextView idade;
    private TextView leococitos;
    private TextView glicemia;
    private TextView ast;
    private TextView ldh;
    private TextView pontuacao;
    private TextView mortalidade;
    private EditText cNome;
    private EditText cIdade;
    private EditText cLeococitos;
    private EditText cGlicemia;
    private EditText cAst;
    private EditText cLdh;
    private CheckBox litiaseBiliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_paciente);

        /////
        Button adc = findViewById(R.id.button2);
        Button voltar = findViewById(R.id.button);
        litiaseBiliar = findViewById(R.id.checkBox);
        cNome = findViewById(R.id.editText);
        cIdade = findViewById(R.id.editText2);
        cLeococitos = findViewById(R.id.editText4);
        cGlicemia = findViewById(R.id.editText8);
        cAst = findViewById(R.id.editText10);
        cLdh = findViewById(R.id.editText12);
        pontuacao = findViewById(R.id.textView18);
        mortalidade = findViewById(R.id.textView19);
        pontuacao.setText("");
        mortalidade.setText("");

        View.OnClickListener trocaTela = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AdicionarPaciente.this, MainActivity.class);
                startActivity(it);
            }
        };
        voltar.setOnClickListener(trocaTela);


        /////
        adc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAL dal = new DAL(AdicionarPaciente.this);
                String nome = cNome.getText().toString();
                Double idade = Double.valueOf(cIdade.getText().toString());
                Double leococitos = Double.valueOf(cLeococitos.getText().toString());
                Double glicemia = Double.valueOf(cGlicemia.getText().toString());
                Double ast = Double.valueOf(cAst.getText().toString());
                Double ldh = Double.valueOf(cLdh.getText().toString());
                int qtd = 0;
                int mor = 0;




                if(litiaseBiliar.isChecked()){
                    if(idade>70){
                        qtd++;
                    }
                    if(leococitos>18000){
                        qtd++;
                    }
                    if(glicemia>12.2){
                        qtd++;
                    }
                    if(ast>250){
                        qtd++;
                    }
                    if(ldh>400){
                        qtd++;
                    }
                }else{
                    if(idade>55){
                        qtd++;
                    }
                    if(leococitos>16000){
                        qtd++;
                    }
                    if(glicemia>11){
                        qtd++;
                    }
                    if(ast>250){
                        qtd++;
                    }
                    if(ldh>350){
                        qtd++;
                    }

                }
                ////





                if(qtd<=2){
                    mor = 2;
                }else if(qtd>=3 && qtd<=4){
                    mor =  15;
                }else if(qtd==5){
                    mor = 40;
                }
                if (dal.insert(nome, idade, leococitos,glicemia, ast,ldh, (double) mor)) {
                    Toast.makeText(AdicionarPaciente.this,
                            "Registro Inserido com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AdicionarPaciente.this,
                            "Erro ao inserir registro!", Toast.LENGTH_LONG).show();
                }


                pontuacao.setText("Pontuação:" + qtd);
                mortalidade.setText("Mortalidade: " + mor+"%");
            }

        });

    }
}
