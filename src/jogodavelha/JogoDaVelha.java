package jogodavelha;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class JogoDaVelha {
    
    //USANDO VARIAVEL PARA SER ACESSADA EM QUALQUER LUGAR! (PARA MUDAR TAMANHO DO GRID CONFORME FOR TROCAR MODO DE JOGO!!
    public static class Tamanho{
        public static int tamanhoGrid=3; //quantidade de colunas que o grid irá ter. OBS: o padrao é 3 colunas(3x3)
        public static int qtdBtn=9;//quantidade de botoes que serao criados. OBS: o padrao é 9 botoes (3x3)
    }
    
    //criando funçao/método que irá solicitar para jogar de novo
    public static void JogarDeNovo(JButton botao[], JLabel informacao, JLabel placar, String player[], JFrame mywindow, int btnAmount, int[] Score){
        informacao.setText("WAITING NAMES...");
        for(int l =0; l<btnAmount;l++){ //for para limpar os campos e habilita-los para clicar novamente
            botao[l].setText("");
            botao[l].setEnabled(true);
            botao[l].setBackground(Color.DARK_GRAY);
        }
        informacao.setForeground(new Color(50,200,50));
        informacao.setText("PLAYER: " + player[0]);
        //placar.setText("<html><body>PLAYER 1(" + player[0] + "): " + Score[0] + "<br>PLAYER 2(" + player[1] + "): " + Score[1] + "</body></html>");

    }
    
    public static void WantPlayAgain(int jogarNovamente, JFrame minhaJanela, int ZerarPlacar, JButton[] botao, JLabel informacao, JLabel placar, String[] player, int[] Score, boolean isRobot){
        jogarNovamente = JOptionPane.showConfirmDialog(null, "Do you wanna play again?", "Play Again", 1); //método "Deseja jogar novamente"
        if(jogarNovamente == 1){ //1=NAO
            minhaJanela.dispose(); //fecha janela atual
        }else{ //SIM
            ZerarPlacar = JOptionPane.showConfirmDialog(null, "Do you want to reset the score?", "Reset Score", 1); //perguntando se quer zerar o placar
            if(ZerarPlacar == 1){ //NAO
                if(!isRobot){ //se o modo de jogo nao for contra o PC, printa os nomes dos dois jogadores, senao printa o jogador 1 e o COMPUTADOR
                    placar.setText("<html><body>PLAYER 1(" + player[0] + "): " + Score[0] + "<br>PLAYER 2(" + player[1] + "): " + Score[1] + "</body></html>"); 
                }else{
                    placar.setText("<html><body>PLAYER 1(" + player[0] + "): " + Score[0] + "<br>COMPUTER: " + Score[1] + "</body></html>");
                }
                JogarDeNovo(botao, informacao, placar, player, minhaJanela, Tamanho.qtdBtn, Score); //começa um novo jogo zerando o placar
            }else{ //SIM
                Score[0]=0; //zera o score player 1
                Score[1]=0;//zera player 2 (ou do computador, caso for esse o modo de jogo)
                if(!isRobot){
                    for(int j=0;j<2;j++){ //lendo o nome dos jogadores usando showInputDialog do JOptionPane
                        player[j]=JOptionPane.showInputDialog(minhaJanela, "Jogador "+ (j+1) + ": ");
                    }
                }else{
                    player[0]=JOptionPane.showInputDialog(minhaJanela, "Jogador 1: ");
                }
                if(!isRobot){
                    placar.setText("<html><body>PLAYER 1(" + player[0] + "): " + Score[0] + "<br>PLAYER 2(" + player[1] + "): " + Score[1] + "</body></html>"); 
                }else{
                    placar.setText("<html><body>PLAYER 1(" + player[0] + "): " + Score[0] + "<br>COMPUTER: " + Score[1] + "</body></html>");
                }
                JogarDeNovo(botao, informacao, placar, player, minhaJanela, Tamanho.qtdBtn, Score); //começa um novo jogo sem zerar o placar
            }
        }
    }
    public static void NovoJogo(int sizeGrid, int btnAmount, boolean isRobot){  
        JButton botao[] = new JButton[btnAmount]; //aqui fica todo o código e funcionalidades do jogo (método chamado sempre que for clicado em "New Game")
        int[] Score = new int[2]; //vai receber o placar do player 1 e do player 2 (ou computador)
        JFrame minhaJanela = new JFrame("Sobre: Jogo da Velha");
        //adicionarMenu(minhaJanela); //adiciona a barra de menu
        minhaJanela.setBounds(150, 50, 1100, 700); //definindo onde a janela vai abrir (qual canto da tela) e qual o tamanho da janela
        minhaJanela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //definindo qual operacao padrao de fechamento sera usado
        
        //CRIANDO A GRADE E A "CAIXA" QUE IRÁ RECEBER OS CONTEUDOS (BOTOES)
        GridLayout grade = new GridLayout(0, sizeGrid); //criando uma grade
        Container caixa = minhaJanela.getContentPane(); //criando uma "caixa" onde serao inseridos os conteudos (botoes...)
        caixa.setLayout(grade); //colocando a grade dentro da "caixa"
        
        //CRIANDO UM VETOR DE STRING PARA RECEBER O NOME DOS DOIS PLAYERS
        String[] player = new String[2]; //variavel do tipo vetor responsavel por receber o nome dos jogadores
        //BARRINHA QUE MOSTRA O PLACAR DO JOGO
        JLabel placar = new JLabel("<html><body>PLAYER 1: 0<br>PLAYER 2: 0</body></html>");
        placar.setFont(new Font("Arial", Font.BOLD, 15));
        placar.setForeground(Color.RED);
        placar.setHorizontalAlignment(SwingConstants.CENTER);
        
        //BARRINHA EM BAIXO QUE MOSTRA O NOME DO JOGADOR QUE IRÁ FAZER A JOGADA
        JLabel informacao = new JLabel("WAITING NAMES...");
        //add(BorderLayout.CENTER, mywindow);
        //add(BorderLayout.SOUTH, informacao);
        informacao.setFont(new Font("Arial", Font.BOLD, 15));
        informacao.setForeground(Color.RED);
        informacao.setHorizontalAlignment(SwingConstants.CENTER);
        
        //ACTION LISTENER
        ActionListener acao_X = new ActionListener() { //criando um evento 
            boolean jogada = true; //Serve para saber quem vai jogar
            boolean vencedor = false; //serve para saber se alguem ganhou
            int value = 0; //serve para contar quantos campos já foram selecionados, se chegar a 9 e nao houver vencedor, dá "velha"
            String x_o; //vai receber o simbolo da jogada (X ou O)
            int jogarNovamente; //vai receber a resposta do InputDialog ("deseja jogar novamente?")
            int ZerarPlacar, valor; //ZerarValor: recebe a resposta do InputDialog("Deseja zerar o placar?") | valor: incrementa quando encontrar botoes nao selecionados
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isRobot){ 
                    placar.setText("<html><body>PLAYER 1(" + player[0] + "): " + Score[0] + "<br>PLAYER 2(" + player[1] + "): " + Score[1] + "</body></html>");
                }else{
                    placar.setText("<html><body>PLAYER 1(" + player[0] + "): " + Score[0] + "<br>COMPUTER: " + Score[1] + "</body></html>");
                }

                ((JButton)e.getSource()).setFont(new Font("Arial", Font.PLAIN, 70));
                value++; //incrementa sempre que um botao for clicado
                
                if(jogada){ //verificando de quem é a vez   
                    x_o = "X";
                    if(!isRobot){
                        ((JButton)e.getSource()).setText("X");
                        informacao.setText("PLAYER 2: " + player[1]);
                    }else{
                        informacao.setText("COMPUTER");
                    }
                    informacao.setForeground(Color.RED);
                    ((JButton)e.getSource()).setText("X"); //setando texto do botao clicado
                    ((JButton)e.getSource()).setBackground(Color.lightGray); //setando cor do fundo
                    ((JButton)e.getSource()).setForeground(Color.white); 
                    if(!isRobot){
                        jogada = !jogada;
                    }
                }else if(!jogada && !isRobot){
                    x_o = "O"; //quando o OnClick for ativado, x_o recebera "O"                    
                    ((JButton)e.getSource()).setText("O");
                    informacao.setText("PLAYER 1: " + player[0]);
                    informacao.setForeground(new Color(50,200,50));
                    ((JButton)e.getSource()).setBackground(Color.pink);
                    ((JButton)e.getSource()).setForeground(Color.black);
                    jogada = !jogada;       
                }
                valor=0;
                for(int pos=0;pos<btnAmount;pos++){ //FOR usado para verificar se há botoes nao selecionados
                    if(botao[pos].getText() == ""){
                        valor++;
                    }
                }
                //System.out.println("VALOR: " + valor);
                if(isRobot){
                    if(valor>0){ //o computador só vai jogar se tiver pelo menos 1 botao nao selecionado
                        x_o = "O";
                        Random pc = new Random();
                        int aleatorio = (int)pc.nextInt(btnAmount);
                        while(botao[aleatorio].getText() != ""){
                            aleatorio = (int)pc.nextInt(btnAmount);
                        }
                        botao[aleatorio].setFont(new Font("Arial", Font.PLAIN, 70));
                        botao[aleatorio].setBackground(Color.pink);
                        botao[aleatorio].setForeground(Color.black);
                        botao[aleatorio].setEnabled(false);
                        botao[aleatorio].setText("O");
                    }
                    informacao.setText("PLAYER 1: " + player[0]);
                    //jogada=true; 
                    //value++;
                    valor --;
                }
                if(valor==0){ //verificando se deu velha, se nao tiver campos em branco, VALUE recebe a quantidade de botoes (para indicar que todos foram usados)
                    value=btnAmount;
                }
                for (int i = 0; i < btnAmount; i++) { //Loop que ira verificar se o botao ta selecionado, se sim, seta ele como desativado -> enabled(false)
                    if (e.getSource() == botao[i]) {
                        botao[i].setEnabled(false); //evitando que o botao seja clicado 2x
                    }
                    
                }
                
                //VERIFICAÇAO DAS JOGADAS, PARA VER SE ALGUÉM GANHOU DE ACORDO COM O MODO(DIFICULDADE) DO JOGO
                switch (sizeGrid) {
                    case 3:
                        //HORIZONTAL
                        if (botao[0].getText().equals(botao[1].getText()) && botao[1].getText().equals(botao[2].getText()) && !botao[0].getText().equals("")) {
                            vencedor = true;
                            if(botao[0].getText() == "X" && botao[1].getText() == "X" && botao[2].getText() == "X" && !botao[0].getText().equals("")){
                                x_o="X";
                            }
                        } else if (botao[3].getText().equals(botao[4].getText()) && botao[4].getText().equals(botao[5].getText()) && !botao[3].getText().equals("")) {
                            vencedor = true;
                            if(botao[3].getText() == "X" && botao[4].getText() == "X" && botao[5].getText() == "X" && !botao[3].getText().equals("")){
                                x_o="X";
                            }
                        } else if (botao[6].getText().equals(botao[7].getText()) && botao[7].getText().equals(botao[8].getText()) && !botao[6].getText().equals("")) {
                            vencedor = true;
                            if(botao[6].getText() == "X" && botao[7].getText() == "X" && botao[8].getText() == "X" && !botao[6].getText().equals("")){
                                x_o="X";
                            }
                        }   //VERTICAL
                        if (botao[0].getText().equals(botao[3].getText()) && botao[3].getText().equals(botao[6].getText()) && !botao[0].getText().equals("")) {
                            vencedor = true;
                            if(botao[0].getText() == "X" && botao[3].getText() == "X" && botao[6].getText() == "X" && !botao[0].getText().equals("")){
                                x_o="X";
                            }
                        } else if (botao[1].getText().equals(botao[4].getText()) && botao[4].getText().equals(botao[7].getText()) && !botao[1].getText().equals("")) {
                            vencedor = true;
                            if(botao[1].getText() == "X" && botao[4].getText() == "X" && botao[7].getText() == "X" && !botao[1].getText().equals("")){
                                x_o="X";
                            }
                        } else if (botao[2].getText().equals(botao[5].getText()) && botao[5].getText().equals(botao[8].getText()) && !botao[2].getText().equals("")) {
                            vencedor = true;
                            if(botao[2].getText() == "X" && botao[5].getText() == "X" && botao[8].getText() == "X" && !botao[2].getText().equals("")){
                                x_o="X";
                            }
                        }   // Diagonal
                        if (botao[0].getText().equals(botao[4].getText()) && botao[4].getText().equals(botao[8].getText()) && !botao[0].getText().equals("")) {
                            vencedor = true; 
                            if(botao[0].getText() == "X" && botao[4].getText() == "X" && botao[8].getText() == "X" && !botao[0].getText().equals("")){
                                x_o="X";
                            }
                        } else if (botao[2].getText().equals(botao[4].getText()) && botao[4].getText().equals(botao[6].getText()) && !botao[2].getText().equals("")) {
                            vencedor = true;
                            if(botao[2].getText() == "X" && botao[4].getText() == "X" && botao[6].getText() == "X" && !botao[2].getText().equals("")){
                                x_o="X";
                            }
                        } break;
                        
                    case 4:
                        //HORIZONTAL
                        int r=0, t=1,k=2, m=3, n=4, o=5; //essas variaveis servem para as posicoes da matriz 4x4
                        for(int count=0; count<sizeGrid;count++){
                            if (botao[r].getText().equals(botao[t].getText()) && botao[t].getText().equals(botao[k].getText()) && botao[k].getText().equals(botao[m].getText()) && !botao[r].getText().equals("")) {
                                vencedor = true;
                            } 
                            if (botao[r].getText().equals("X") && botao[t].getText().equals("X") && botao[k].getText().equals("X") && botao[m].getText().equals("X") && !botao[r].getText().equals("")) {
                                x_o="X";
                            }r+=4; t+=4; k+=4; m+=4;
                        }                        
                        //VERTICAL
                        r=4; t=8; k=12;
                        for(int cont = 0; cont < sizeGrid; cont++){
                            if (botao[cont].getText().equals(botao[r].getText()) && botao[r].getText().equals(botao[t].getText()) && botao[t].getText().equals(botao[k].getText()) && !botao[cont].getText().equals("")) {
                                vencedor = true;
                            }
                            if (botao[cont].getText().equals("X") && botao[r].getText().equals("X") && botao[t].getText().equals("X") && botao[k].getText().equals("X") && !botao[cont].getText().equals("")) {
                                x_o="X";
                            }r++; t++; k++;
                        }
                        // DIAGONAL
                        if (botao[0].getText().equals(botao[5].getText()) && botao[5].getText().equals(botao[10].getText()) && botao[10].getText().equals(botao[15].getText()) && !botao[0].getText().equals("")) {
                            vencedor = true;
                            if (botao[0].getText().equals("X") && botao[5].getText().equals("X") && botao[10].getText().equals("X") && botao[15].getText().equals("X")&& !botao[0].getText().equals("")) {
                                x_o="X";
                            }
                        } else if (botao[3].getText().equals(botao[6].getText()) && botao[6].getText().equals(botao[9].getText()) && botao[9].getText().equals(botao[12].getText()) && !botao[3].getText().equals("")) {
                            vencedor = true;
                            if (botao[3].getText().equals("X") && botao[6].getText().equals("X") && botao[9].getText().equals("X") && botao[12].getText().equals("X")&& !botao[3].getText().equals("")) {
                                x_o="X";
                            }
                        }
                        break;
                        
                    case 5:
                        r=0;t=1;k=2;m=3; n=4; //essas variaveis servem para as posicoes da matriz 5x5
                        for(int count=0; count<sizeGrid;count++){
                            if (botao[r].getText().equals(botao[t].getText()) && botao[t].getText().equals(botao[k].getText()) && botao[k].getText().equals(botao[m].getText()) && botao[m].getText().equals(botao[n].getText()) && !botao[r].getText().equals("")) {
                                vencedor = true;
                            } 
                            if (botao[r].getText().equals("X") && botao[t].getText().equals("X") && botao[k].getText().equals("X") && botao[m].getText().equals("X") && botao[n].getText().equals("X") && !botao[r].getText().equals("")) {
                                x_o="X";
                            }r+=5; t+=5; k+=5; m+=5; n+=5;
                        }
                        
                        //VERTICAL
                        r=5; t=10; k=15; m=20;
                        for(int cont = 0; cont < sizeGrid; cont++){
                            if (botao[cont].getText().equals(botao[r].getText()) && botao[r].getText().equals(botao[t].getText()) && botao[t].getText().equals(botao[k].getText()) && botao[k].getText().equals(botao[m].getText()) && !botao[cont].getText().equals("")) {
                                vencedor = true;
                            }
                            if (botao[cont].getText().equals("X") && botao[r].getText().equals("X") && botao[t].getText().equals("X") && botao[k].getText().equals("X") && botao[m].getText().equals("X") && !botao[cont].getText().equals("")) {
                                x_o="X";
                            }r++; t++; k++; m++;
                        }
                        // DIAGONAL
                        if (botao[0].getText().equals(botao[6].getText()) && botao[6].getText().equals(botao[12].getText()) && botao[12].getText().equals(botao[18].getText()) && botao[18].getText().equals(botao[24].getText()) && !botao[0].getText().equals("")) {
                            vencedor = true;
                            if (botao[0].getText().equals("X") && botao[6].getText().equals("X") && botao[12].getText().equals("X") && botao[18].getText().equals("X") && botao[24].getText().equals("X") && !botao[0].getText().equals("")) {
                                x_o="X";
                            }
                        } else if (botao[4].getText().equals(botao[8].getText()) && botao[8].getText().equals(botao[12].getText()) && botao[12].getText().equals(botao[16].getText()) && botao[16].getText().equals(botao[20].getText())&& !botao[4].getText().equals("")) {
                            vencedor = true;
                            if (botao[4].getText().equals("X") && botao[8].getText().equals("X") && botao[12].getText().equals("X") && botao[16].getText().equals("X") && botao[20].getText().equals("X") && !botao[4].getText().equals("")) {
                                x_o="X";
                            }
                        }
                        break;
                        
                    case 6:
                        r=0;t=1;k=2;m=3; n=4; o=5; //essas variaveis servem para as posicoes das colunas (0,1,2,3,4,5) da matriz 6x6
                        
                        //HORIZONTAL
                        for(int count=0; count<sizeGrid;count++){
                            if (botao[r].getText().equals(botao[t].getText()) && botao[t].getText().equals(botao[k].getText()) && botao[k].getText().equals(botao[m].getText()) && botao[m].getText().equals(botao[n].getText()) && botao[n].getText().equals(botao[o].getText()) && !botao[r].getText().equals("")) {
                                vencedor = true;
                            } 
                            if (botao[r].getText().equals("X") && botao[t].getText().equals("X") && botao[k].getText().equals("X") && botao[m].getText().equals("X") && botao[n].getText().equals("X") && botao[o].getText() == "X" && !botao[r].getText().equals("")) {
                                x_o="X";
                            } r+=6; t+=6; k+=6; m+=6; n+=6; o+=6;
                        }                        
                        //VERTICAL
                        r=6; t=12; k=18; m=24; n=30;
                        for(int cont = 0; cont < sizeGrid; cont++){
                            if (botao[cont].getText().equals(botao[r].getText()) && botao[r].getText().equals(botao[t].getText()) && botao[t].getText().equals(botao[k].getText()) && botao[k].getText().equals(botao[m].getText()) && botao[m].getText().equals(botao[n].getText()) && !botao[cont].getText().equals("")) {
                                vencedor = true;
                            }
                            if (botao[cont].getText().equals("X") && botao[r].getText().equals("X") && botao[t].getText().equals("X") && botao[k].getText().equals("X") && botao[m].getText().equals("X") && botao[n].getText() == "X" && !botao[cont].getText().equals("")) {
                                x_o="X";
                            } r++; t++; k++; m++; n++;
                        }
                        // DIAGONAL
                        if (botao[0].getText().equals(botao[7].getText()) && botao[7].getText().equals(botao[14].getText()) && botao[14].getText().equals(botao[21].getText()) && botao[21].getText().equals(botao[28].getText()) && botao[28].getText().equals(botao[35].getText()) && !botao[0].getText().equals("")) {
                            vencedor = true;
                            if (botao[0].getText().equals("X") && botao[7].getText().equals("X") && botao[14].getText().equals("X") && botao[21].getText().equals("X") && botao[28].getText().equals("X") && botao[35].getText() == "X" && !botao[0].getText().equals("")) {
                                x_o="X";
                            } 
                        } else if (botao[5].getText().equals(botao[10].getText()) && botao[10].getText().equals(botao[15].getText()) && botao[15].getText().equals(botao[20].getText()) && botao[20].getText().equals(botao[25].getText()) && botao[25].getText().equals(botao[30].getText()) && !botao[5].getText().equals("")) {
                            vencedor = true;
                            if (botao[5].getText().equals("X") && botao[10].getText().equals("X") && botao[15].getText().equals("X") && botao[20].getText().equals("X") && botao[25].getText().equals("X") && botao[30].getText() == "X" && !botao[5].getText().equals("")) {
                                x_o="X";
                            } 
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "GAME MODE INCORRECT", "ERROR", 1);
                        break;
                }
                //RESULTADO
                if(vencedor){ //se houver algum vencedor (vencedor=true)
                    if(x_o == "X"){ //se o ultimo a jogar for o jogador que começou com "X"
                        Score[0]+=1;
                        JOptionPane.showMessageDialog(null, "Player " + player[0] + " wins!", "TIC-TAC-TOE", JOptionPane.INFORMATION_MESSAGE);
                    }else if(x_o == "O"){
                        Score[1]+=1;
                        if(!isRobot){
                            JOptionPane.showMessageDialog(null, "Player " + player[1] + " wins!", "TIC-TAC-TOE", JOptionPane.INFORMATION_MESSAGE); 
                        }else{
                            JOptionPane.showMessageDialog(null, "COMPUTER wins!", "TIC-TAC-TOE", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    vencedor=false;
                    value = 0;
                    jogada = true; 
                    WantPlayAgain(jogarNovamente, minhaJanela, ZerarPlacar, botao, informacao, placar, player, Score, isRobot);
                    
                }else if(!vencedor && value == btnAmount){ //se a variavel "vencedor" nao receber "true" e todas as posicoes forem selecionadas, dá velha
                    JOptionPane.showMessageDialog(null, "DEU VELHA", "I'M SORRY, BUT... :(", JOptionPane.ERROR_MESSAGE);
                    value = 0;
                    jogada = true; 
                    WantPlayAgain(jogarNovamente, minhaJanela, ZerarPlacar, botao, informacao, placar, player, Score, isRobot);

                }
            }
        };
        
        for (int i = 0; i < btnAmount; i++) {
            botao[i] = new JButton(); //criando os botoes (usando vetor)
            botao[i].addActionListener(acao_X); //adicionando o evento
            caixa.add(botao[i]); //add o botao na caixa
            caixa.add(informacao);
            botao[i].setBackground(Color.DARK_GRAY);
        }
        minhaJanela.add(placar);
        minhaJanela.setVisible(true);//setando a visibilidade da janela (true)
        if(!isRobot){ //se for false
            for(int j=0;j<2;j++){ //lendo o nome dos jogadores usando showInputDialog do JOptionPane
                player[j]=JOptionPane.showInputDialog(minhaJanela, "Player "+ (j+1) + ": ");
            }
            placar.setText("<html><body>PLAYER 1(" + player[0] + "): " + Score[0] + "<br>PLAYER 2(" + player[1] + "): " + Score[1] + "</body></html>");

        }else{//se for true
            player[0]=JOptionPane.showInputDialog(minhaJanela, "Player 1:");
            placar.setText("<html><body>PLAYER 1(" + player[0] + "): " + Score[0] + "<br>COMPUTER: " + Score[1] + "</body></html>");
            
        }
        
        informacao.setText("PLAYER 1: " + player[0]);
        informacao.setForeground(new Color(50,200,50));
    }
    
    // FUNÇÂO PARA ADICIONAR O MENU
    public static void adicionarMenu(JFrame mywindow){
        JMenu newGameMenu, exitMenu, gameMode, about;
        JMenuItem twoPlayers, playerVsComputer, gmode3x3, gmode4x4, gmode5x5, gmode6x6;
        JMenuBar menuBar = new JMenuBar();
        //menu novo jogo
        newGameMenu = new JMenu("New Game"); menuBar.add(newGameMenu);
        twoPlayers = new JMenuItem("Player1 vs Player2"); newGameMenu.add(twoPlayers);
        playerVsComputer = new JMenuItem("Player Vs PC"); newGameMenu.add(playerVsComputer);
        //modo de jogo
        gameMode = new JMenu("Set Game Mode"); menuBar.add(gameMode);
        gmode3x3 = new JMenuItem("Game 3x3"); gameMode.add(gmode3x3);
        gmode4x4 = new JMenuItem("Game 4x4"); gameMode.add(gmode4x4);
        gmode5x5 = new JMenuItem("Game 5x5"); gameMode.add(gmode5x5);
        gmode6x6 = new JMenuItem("Game 6x6"); gameMode.add(gmode6x6);
        //Sobre
        about = new JMenu("About Game"); menuBar.add(about);
        //sair
        exitMenu = new JMenu("Quit Game"); menuBar.add(exitMenu);  
        //fazendo minha janela setar o menuBar
        mywindow.setJMenuBar(menuBar);
        mywindow.setSize(500,500);//setando tamanho  

        //EVENTO PARA O BOTAO DE MENU 2 contra 2
        ActionListener P1xP2Click = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isRobot=false;
                NovoJogo(Tamanho.tamanhoGrid, Tamanho.qtdBtn, isRobot);
            }
        };
        
        //EVENTO PARA O BOTAO DE MENU Contra o pc
        ActionListener P1xPcClick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isRobot=true;
                NovoJogo(Tamanho.tamanhoGrid, Tamanho.qtdBtn, isRobot);
            }
        };
        
        //EVENTO PARA O BOTAO DE MENU SAIR
        MenuListener ExitClick = new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                int quit = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game?", "Quit Game", 1);
                if(quit == 0){ //1=NO
                    mywindow.dispose();
                }
            }
            @Override public void menuCanceled(MenuEvent e) {} @Override public void menuDeselected(MenuEvent e) {}
        };
        
        //EVENTO PARA O BOTAO DE MENU SOBRE
        MenuListener AboutClick = new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                JFrame about = new JFrame("About Game");
                about.setAlwaysOnTop(true);
                about.setBounds(275, 30, 800, 600); //definindo onde a janela vai abrir (qual canto da tela) e qual o tamanho da janela
                about.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //definindo qual operacao padrao de fechamento sera usado
                Sobre(about);
            }
            @Override public void menuCanceled(MenuEvent e) {} @Override public void menuDeselected(MenuEvent e) {}
        };
        
        // Menssagens de verificação de escolha
        ActionListener gMode3x3 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Tamanho.tamanhoGrid = 3; Tamanho.qtdBtn = 9;
                JOptionPane.showMessageDialog(null, "Game Mode Selected: 3 x 3", "Game Mode", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        ActionListener gMode4x4 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Tamanho.tamanhoGrid = 4; Tamanho.qtdBtn = 16;
                JOptionPane.showMessageDialog(null, "Game Mode Selected: 4 x 4", "Game Mode", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        ActionListener gMode5x5 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Tamanho.tamanhoGrid = 5; Tamanho.qtdBtn = 25;
                JOptionPane.showMessageDialog(null, "Game Mode Selected: 5 x 5", "Game Mode", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        ActionListener gMode6x6 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Tamanho.tamanhoGrid = 6; Tamanho.qtdBtn = 36;
                JOptionPane.showMessageDialog(null, "Game Mode Selected: 6 x 6", "Game Mode", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        
        twoPlayers.addActionListener(P1xP2Click); //entrar no modo player vs player
        playerVsComputer.addActionListener(P1xPcClick); //entrar no modo player vs pc
        about.addMenuListener(AboutClick); //Sobre
        exitMenu.addMenuListener(ExitClick); //Sair
        gmode3x3.addActionListener(gMode3x3);
        gmode4x4.addActionListener(gMode4x4);
        gmode5x5.addActionListener(gMode5x5);
        gmode6x6.addActionListener(gMode6x6);
        
    }
    
    //FUNÇÂO PARA JANELA SOBRE
    public static void Sobre(JFrame janela){
        
        String sobre = "Este jogo foi criado por: Vinicius Bernardo e Iago Gabriel - ADS 4º Periodo\n";
        String origem = "\nO Jogo da Velha originou-se na Inglaterra quando mulheres ao \n"
        + "fim de tarde se reuniam para tomar o chá, bordar e brincar. O jogo da \n" 
        + "velha era jogado pelas senhoras de mais idade já que as mesmas não \n" 
        + "enxergavam bem,  e não podiam então realizar seus bordados;\n"
        + "a opção de diversão então era o Jogo da Velha.\n";
        String regras = "\nREGRAS\n\n- O tabuleiro  é uma matriz  de três linhas por três colunas (No jogo padrao, mas há como aumentar de tamanho).\n" 
        + "- Dois jogadores escolhem uma marcação cada um, geralmente um círculo (O) e um xis (X).\n"
        + "- Os jogadores jogam alternadamente, uma marcação por vez, numa lacuna que esteja vazia.\n"
        + "- O objetivo é conseguir colocar apenas círculos ou xis em linha, quer horizontal, vertical ou diagonal,\n"
        + " e ao mesmo tempo, quando possível, impedir o adversário de ganhar na próxima jogada.\n"
        + "- Quando um jogador conquista o objetivo, costuma-se riscar os símbolos.";
        JTextArea AreaTexto = new JTextArea(sobre);
        AreaTexto.append(origem);
        AreaTexto.append(regras);
        AreaTexto.setFont(new Font("Arial", Font.BOLD, 20));
        AreaTexto.setPreferredSize(new Dimension(300, 200));
        AreaTexto.setLineWrap(true); // Ao chegar no fim da linha segue para a proxima em baixo
        AreaTexto.setWrapStyleWord(true); // Não deixa a palavra ficar pela metade
        AreaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(AreaTexto,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        janela.add(scrollPane);
        janela.setVisible(true);//setando a visibilidade da janela (true)
    }
     
    //PRINCIPAL
    public static void main(String[] args) {
        JFrame mywindow = new JFrame("Sobre: Jogo da Velha");
        adicionarMenu(mywindow); //adiciona a barra de menu
        mywindow.setBounds(250, 50, 800, 600); //definindo onde a janela vai abrir (qual canto da tela) e qual o tamanho da janela
        mywindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //definindo qual operacao padrao de fechamento sera usado
        Sobre(mywindow);
    }
    
}