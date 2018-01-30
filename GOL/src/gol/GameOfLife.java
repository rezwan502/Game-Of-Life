package gol;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;
import java.awt.Image;


public class GameOfLife extends javax.swing.JFrame {
    final int wid=60, hei=60;
    boolean [][] currentMove = new boolean[hei][wid], nextmove = new boolean[hei][wid];
    boolean play;
    Image offscrImg;
    Graphics offscrGraph;
    
 public GameOfLife() {
        initComponents();
        offscrImg = createImage(jPanel1.getWidth(),jPanel1.getHeight());
        offscrGraph= offscrImg.getGraphics();
        Timer time = new Timer();
        TimerTask task= new TimerTask(){
           public void run(){
               if(play){
               for(int i=0;i<hei;i++){
                   for(int j=0;j<wid;j++){
                       nextmove[i][j]= decide(i,j);
                   }
               }               
               for(int i=0;i<hei;i++){
                   for(int j=0;j<wid;j++){
                       currentMove[i][j]= nextmove[i][j];
                   }
               }
               repain();
               
               }
           } 
        };
        time.scheduleAtFixedRate(task, 0, 500);
        repain();
    }
 private boolean decide(int i, int j){
     int neighbors=0;
     if(j>0){
         if(currentMove[i][j-1]) neighbors++;
         if(i>0) if(currentMove[i-1][j-1]) neighbors++;
         if(i<hei-1) if(currentMove[i+1][j-1]) neighbors++;
     }
      if(j<wid-1){
         if(currentMove[i][j+1]) neighbors++;
         if(i>0) if(currentMove[i-1][j+1]) neighbors++;
         if(i<hei-1) if(currentMove[i+1][j+1]) neighbors++;
     }
       if(i>0) if(currentMove[i-1][j]) neighbors++;
       if(i<hei-1) if(currentMove[i+1][j]) neighbors++;
       if(neighbors == 3) return true;
       if(currentMove[i][j] && neighbors == 2) return true;
       return false;
 }
 
 private void repain(){
     
     offscrGraph.setColor(jPanel1.getBackground());
     offscrGraph.fillRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());
     for(int i=0;i<hei;i++){
         for(int j=0;j<wid;j++){
                  if(currentMove[i][j]){
                      offscrGraph.setColor(Color.YELLOW);
                  int x=j*jPanel1.getWidth()/wid;
                  int y=i*jPanel1.getHeight()/hei;

                  offscrGraph.fillRect(x, y, jPanel1.getWidth()/wid, jPanel1.getHeight()/hei);
         }
         }
     }
     
     offscrGraph.setColor(Color.BLACK);
     for(int i=1; i<hei; i++)
     {
         int y=i*jPanel1.getHeight()/hei;
         offscrGraph.drawLine(0, y, jPanel1.getWidth(), y);
     }
     
     for(int j=1; j<wid; j++)
     {
         int x=j*jPanel1.getWidth()/wid;
         offscrGraph.drawLine(x, 0, x,jPanel1.getHeight());
     }
     
     jPanel1.getGraphics().drawImage(offscrImg, 0, 0, jPanel1);
        
 }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Game Of Life");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );

        jButton2.setText("Play");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        int j = wid * evt.getX()/ jPanel1.getWidth();
        int i = hei * evt.getY()/ jPanel1.getHeight();
        currentMove[i][j]=!currentMove[i][j];
        repain();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        offscrImg = createImage(jPanel1.getWidth(),jPanel1.getHeight());
        offscrGraph= offscrImg.getGraphics();
        repain();
  
    }//GEN-LAST:event_jPanel1ComponentResized

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        play = !play;
        if(play) jButton2.setText("Pause");
        else jButton2.setText("Play");
        //repain();

    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameOfLife().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
