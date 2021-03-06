package br.ufjf.dcc196.matheus.jokenpo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random dado= new Random();

    public enum Jogada{
        PEDRA(0), PAPEL(1), TESOURA(2),LAGARTO(3),SPOCK(4) ;
        private final int valor;
        Jogada(int valor){
            this.valor=valor;

        }
    }

    public enum Resultado{
        DERROTA(-1),EMPATE(0),VITORIA(1);
        private final int valor;
        Resultado(int valor){
            this.valor=valor;
        }
    }

    public static final Resultado TABELA[] []= {
            {Resultado.EMPATE},{Resultado.DERROTA},{Resultado.VITORIA},{Resultado.VITORIA},{Resultado.DERROTA},
            {Resultado.VITORIA},{Resultado.EMPATE},{Resultado.DERROTA},{Resultado.DERROTA},{Resultado.VITORIA},
            {Resultado.DERROTA},{Resultado.VITORIA},{Resultado.EMPATE},{Resultado.VITORIA},{Resultado.DERROTA},
            {Resultado.DERROTA},{Resultado.VITORIA},{Resultado.DERROTA},{Resultado.EMPATE},{Resultado.VITORIA},
            {Resultado.VITORIA},{Resultado.DERROTA},{Resultado.VITORIA},{Resultado.DERROTA},{Resultado.EMPATE},
    };

    private Integer pontosCPU = 0;
    private Integer pontosPlayer = 0;

    private Button pedraButton;
    private Button papelButton;
    private Button tesouraButton;
    private Button ButtonLargato;
    private Button ButtonSpock;

    private ProgressBar progressBarCpu;
    private ProgressBar progressBarPlayer;

    private TextView textViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pedraButton= findViewById(R.id.PedraButton);
        papelButton= findViewById(R.id.PapelButton);
        tesouraButton= findViewById(R.id.TesouraButton);
        ButtonLargato= findViewById(R.id.buttonLagarto);
        ButtonSpock= findViewById(R.id.buttonSpock);

        progressBarPlayer=findViewById(R.id.progressBarPlayer);
        progressBarCpu= findViewById(R.id.progressBarCpu);
        textViewStatus= findViewById(R.id.TextViewStatus);

    }
    public void ButtonPedraClick(View view){
        rodada(Jogada.PEDRA);
    }

    public void ButtonPapelClick(View view){
        rodada(Jogada.PAPEL);
    }
    public void ButtonTesouraClick(View view){rodada(Jogada.TESOURA);}
    public void ButtonLagartoClick(View view){rodada(Jogada.LAGARTO);}
    public void ButtonSpockClick(View view){rodada(Jogada.SPOCK);}

    public void rodada(Jogada jogada){
        Jogada jogadaCpu= Jogada.values()[dado.nextInt(5)];
        switch (TABELA[jogada.valor][jogadaCpu.valor]){
            case VITORIA:
                pontosPlayer += 3;
                Toast.makeText(this,"Voc?? ganhou a rodada!", Toast.LENGTH_SHORT).show();
                break;
            case EMPATE:
                pontosPlayer +=1;
                pontosCPU += 1;
                Toast.makeText(this,"Voc?? empatou a rodada!", Toast.LENGTH_SHORT).show();
                break;
            case DERROTA:
                pontosCPU +=3;
                Toast.makeText(this,"Voc?? perdeu a rodada!", Toast.LENGTH_SHORT).show();
                break;

        }
        atualizaStatus();
    }

    private void atualizaStatus() {
        progressBarCpu.setProgress(pontosCPU);
        progressBarPlayer.setProgress(pontosPlayer);
        if (pontosPlayer < 15 && pontosCPU < 15) {
            textViewStatus.setText("Escolha uma op????o");
        } else if (pontosCPU < 15 && pontosPlayer >= 15) {
            textViewStatus.setText("Voc?? ganhou o torneio!!!!");
            iniciaTorneio();
        } else if (pontosCPU >= 15 && pontosPlayer < 15){
            textViewStatus.setText("Voc?? perdeu o torneio!!!!");
            iniciaTorneio();
        }else {
            textViewStatus.setText("O torneio terminou empatado");
            iniciaTorneio();
        }
    }

    private void iniciaTorneio(){
        pontosPlayer=0;
        pontosCPU=0;
    }

    public void TextViewClick(View view){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Reiniciar o torneio?");
        dialogBuilder.setTitle("Deseja reiniciar o torneio zerando o estado atual?");
        dialogBuilder.setPositiveButton("Reinicar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                iniciaTorneio();
                atualizaStatus();
            }
        });
        dialogBuilder.create();
        dialogBuilder.show();
    }






















}