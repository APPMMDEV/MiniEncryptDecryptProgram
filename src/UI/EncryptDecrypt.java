package UI;

import Encrypt.EncryptDecryptHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptDecrypt extends JFrame {
    private JPanel mainPanel;
    private JTextField secretKey;
    private JTextField orginalText;
    private JButton encryptCopy;
    private JButton decryptCopy;
    private JTextArea decryptedText;
    private JTextArea encryptedText;
    private JButton encryptBtn;
    private JButton decryptBtn;

    private  String keySecret,TextOrginal,TextEncrypted,TextDecrypted;


    public static void main(String[] args) {
        EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
        encryptDecrypt.setContentPane(encryptDecrypt.mainPanel);
        encryptDecrypt.setTitle("AES Encrypt Decrypt Mini Program (MCoder )");
        encryptDecrypt.setSize(800, 600);
        encryptDecrypt.setVisible(true);
        encryptDecrypt.setDefaultCloseOperation(3);
        ImageIcon imageIcon = new ImageIcon(encryptDecrypt.getClass().getResource("secure.png"));
        encryptDecrypt.setIconImage(imageIcon.getImage());




    }
    public EncryptDecrypt() {


        encryptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                keySecret = secretKey.getText().toString();

                TextOrginal = orginalText.getText().toString();
                if (keySecret.isEmpty()){

                    JOptionPane.showMessageDialog(null,"Secret Key is Empty");
                    secretKey.requestFocus();


                }else if (TextOrginal.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Orginal Text is Empty");
                    orginalText.requestFocus();

                }else {

                    TextEncrypted = EncryptDecryptHelper.encrypted(TextOrginal,keySecret);

                    encryptedText.setText(TextEncrypted);
                }
            }
        });


        decryptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                keySecret = secretKey.getText().toString();

                TextEncrypted = encryptedText.getText().toString();
                if (keySecret.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Secret Key is Empty!!!");

                    secretKey.requestFocus();


                }else if (TextEncrypted.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Encrypted Text is Empty!!!");



                }else {

                    TextDecrypted = EncryptDecryptHelper.decrypted(TextEncrypted,keySecret);

                    decryptedText.setText(TextDecrypted);
                }
            }
        });

        encryptCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CopyText = encryptedText.getText().toString();

                if (CopyText.isEmpty()){

                    JOptionPane.showMessageDialog(null,"Encrypted Text is Empty");
                }else {
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    StringSelection stringSelection = new StringSelection(CopyText);
                    clipboard.setContents(stringSelection,stringSelection);
                    JOptionPane.showMessageDialog(null,"Encrypted Text Copied!!!"+"\n"+CopyText);

                }


            }
        });

        decryptCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CopyText = decryptedText.getText().toString();

                if (CopyText.isEmpty()){

                    JOptionPane.showMessageDialog(null,"Decrypted Text is Empty");
                }else {

                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    StringSelection stringSelection = new StringSelection(CopyText);
                    clipboard.setContents(stringSelection, stringSelection);
                    JOptionPane.showMessageDialog(null, "Descrypted Text Copied!!!" + "\n" + CopyText);


                }
            }
        });

    }


}
