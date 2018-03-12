package lightsout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

class GameMain extends JFrame implements ActionListener,MouseListener{
	final String SAVE_PASS = "../JavaLightsOut/save.save";
	final String ICON_PASS = "../JavaLightsOut/icon/";
	final int dSize=55;	//スイッチのサイズを指定
	int con,scr,lv=1;	//手数,スコア,レベル
	JLabel label1,label2;
	ImageIcon[] icon = {new ImageIcon(ICON_PASS+"sw01.png"),
						new ImageIcon(ICON_PASS+"sw02.png"),
						new ImageIcon(ICON_PASS+"sw03.png"),
						new ImageIcon(ICON_PASS+"sw04.png"),
						new ImageIcon(ICON_PASS+"sw05.png")};
	JButton[] btn = {new JButton("やりなおし"),
					 new JButton(icon[0]),
					 new JButton(icon[0]),
					 new JButton(icon[0]),
					 new JButton(icon[0]),
					 new JButton(icon[0]),
					 new JButton(icon[0]),
					 new JButton(icon[0]),
					 new JButton(icon[0]),
					 new JButton(icon[0])};
	JPopupMenu cMenu;

	GameMain(String title){
		setTitle(title);
		setBounds( 100, 100, (dSize*3)+30, (dSize*5)+25);

		JPanel p = new JPanel();
		p.addMouseListener(this);

	    label1 = new JLabel("");
	    label1.setHorizontalAlignment(JLabel.CENTER);
	    label1.addMouseListener(this);
	    label2 = new JLabel(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
	    label2.setHorizontalAlignment(JLabel.CENTER);
	    label2.addMouseListener(this);

		for(int i = 0;i <= 9;i++) {
			btn[i].setPreferredSize(new Dimension(dSize,dSize));
			btn[i].setBackground(new Color(100,100,100));
			btn[i].setBorder(new LineBorder(Color.black, 3,true));
			btn[i].addActionListener(this);
			btn[i].addMouseListener(this);
			if(i == 0) {
				btn[0].setFont(new Font("ＭＳ ゴシック", Font.BOLD, (dSize/3)));
				btn[0].setPreferredSize(new Dimension((dSize*3)-10,dSize-10));
				btn[i].setBackground(Color.LIGHT_GRAY);
				btn[0].setMnemonic(KeyEvent.VK_0);
				btn[0].setMnemonic(KeyEvent.VK_NUMPAD0);
				btn[0].setActionCommand("btn_Reset");
			}else if(i == 1) {
				btn[1].setMnemonic(KeyEvent.VK_1);
				btn[1].setMnemonic(KeyEvent.VK_NUMPAD1);
				btn[1].setActionCommand("btn_1");
			}else if(i == 2) {
				btn[2].setMnemonic(KeyEvent.VK_2);
				btn[2].setMnemonic(KeyEvent.VK_NUMPAD2);
				btn[2].setActionCommand("btn_2");
			}else if(i == 3) {
				btn[3].setMnemonic(KeyEvent.VK_3);
				btn[3].setMnemonic(KeyEvent.VK_NUMPAD3);
				btn[3].setActionCommand("btn_3");
			}else if(i == 4) {
				btn[4].setMnemonic(KeyEvent.VK_4);
				btn[4].setMnemonic(KeyEvent.VK_NUMPAD4);
				btn[4].setActionCommand("btn_4");
			}else if(i == 5) {
				btn[5].setMnemonic(KeyEvent.VK_5);
				btn[5].setMnemonic(KeyEvent.VK_NUMPAD5);
				btn[5].setActionCommand("btn_5");
			}else if(i == 6) {
				btn[6].setMnemonic(KeyEvent.VK_6);
				btn[6].setMnemonic(KeyEvent.VK_NUMPAD6);
				btn[6].setActionCommand("btn_6");
			}else if(i == 7) {
				btn[7].setMnemonic(KeyEvent.VK_7);
				btn[7].setMnemonic(KeyEvent.VK_NUMPAD7);
				btn[7].setActionCommand("btn_7");
			}else if(i == 8) {
				btn[8].setMnemonic(KeyEvent.VK_8);
				btn[8].setMnemonic(KeyEvent.VK_NUMPAD8);
				btn[8].setActionCommand("btn_8");
			}else if(i == 9) {
				btn[9].setMnemonic(KeyEvent.VK_9);
				btn[9].setMnemonic(KeyEvent.VK_NUMPAD9);
				btn[9].setActionCommand("btn_9");
			}
		}

		//右クリックメニューの作成
		cMenu = new JPopupMenu();
	    JMenuItem resetMenuItem = new JMenuItem("ゲームのリセット");
	    resetMenuItem.setMnemonic(KeyEvent.VK_R);
	    resetMenuItem.addActionListener(this);
	    resetMenuItem.setActionCommand("all_reset");
	    JMenu dataMenu = new JMenu("セーブ&ロード");
	    JMenuItem saveMenu = new JMenuItem("セーブ");
	    saveMenu.addActionListener(this);
	    saveMenu.setActionCommand("save");
	    JMenuItem loadMenu = new JMenuItem("ロード");
	    loadMenu.addActionListener(this);
	    loadMenu.setActionCommand("load");
	    JMenuItem helpMenuItem = new JMenuItem("ヘルプ");
	    helpMenuItem.setMnemonic(KeyEvent.VK_H);
	    helpMenuItem.addActionListener(this);
	    helpMenuItem.setActionCommand("help");

	    dataMenu.add(saveMenu);
	    dataMenu.addSeparator();
	    dataMenu.add(loadMenu);
	    cMenu.add(resetMenuItem);
	    cMenu.addSeparator();
	    cMenu.add(dataMenu);
	    cMenu.addSeparator();
	    cMenu.add(helpMenuItem);
//各ボタン(スイッチ)の追加
		p.add(btn[7]);
		p.add(btn[8]);
		p.add(btn[9]);
		p.add(btn[4]);
		p.add(btn[5]);
		p.add(btn[6]);
		p.add(btn[1]);
		p.add(btn[2]);
		p.add(btn[3]);
		p.add(btn[0]);

		add(p);
		add(label1, BorderLayout.PAGE_END);
		add(label2, BorderLayout.PAGE_START);
//初期化処理
		RandomSet();
	}
//マウスのクリック
	public void mouseReleased(MouseEvent e){
		showPopup(e);
	}

	public void mousePressed(MouseEvent e){
		showPopup(e);
	}

	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}

	private void showPopup(MouseEvent e){
		if (e.isPopupTrigger()) {
			cMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

//ボタンが押された時
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();

		if (cmd.equals("all_reset")){
			con=0;
			lv=1;
			scr=0;
			label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
			label1.setText("ゲームをリセットします");
			RandomSet();
/*セ*/	}else if (cmd.equals("save")){
			try {
				PrintWriter out    = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(SAVE_PASS),"Shift_JIS")));
				out.write(String.format("%d,%d,%d",lv,scr,con));
				out.close();            // 出力ファイルを閉じる
				label1.setText("セーブしました");
	        } catch (IOException e1) {   //セーブデータが見つからなかった場合
				File newfile = new File(SAVE_PASS);
				try{
					if (newfile.createNewFile()){	//新しくセーブデータを作成
						label1.setText("セーブデータを作成しました");
					}else{	//そもそもセーブデータの作成に失敗した場合
						label1.setText("セーブに失敗しました");
					}
				}catch(IOException e2){	//セーブデータ作成時にエラーが出た場合
					System.exit(-1);
				}finally {
					newfile = null;
				}
			}
/*ロ*/	}else if (cmd.equals("load")){
			try{
				BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(SAVE_PASS),"Shift_JIS"));
				String data_array[] = in.readLine().split(",");
				lv	 = Integer.parseInt(data_array[0]);          // セーブデータからレベルの取得
				if(lv<=0)
					lv=1;
				scr  = Integer.parseInt(data_array[1]);          // セーブデータからスコアの取得
				if(scr<0)
					scr=0;
				con  = Integer.parseInt(data_array[2]);          // セーブデータから手数の取得
				if(con<0)
					con=0;
				data_array = null;
				in.close();             // 入力ファイルを閉じる
				label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
				RandomSet();
				label1.setText("ロード完了");
			}catch (IOException e1) {   //セーブデータが見つからなかった場合
				label1.setText("ロードに失敗しました");
			}catch(NumberFormatException e2){	//セーブデータに異常な値が入っている場合
				System.exit(-1);
			}
/*ヘ*/	}else if (cmd.equals("help")){
			JLabel helplabel = new JLabel("全てのスイッチを同じにすればクリアです。");
			String helpNo = "ヘルプ No.1";
			int[] init_key = {(int) System.currentTimeMillis(), (int) Runtime.getRuntime().freeMemory()};
			Sfmt rnd = new Sfmt(init_key);
			int hn = rnd.NextInt(6);
			if(hn == 1) {
				helpNo = "ヘルプ No.2";
				helplabel.setText("『やりなおし』を押すとスイッチの配置がリセットされます");
			}else if(hn == 2) {
				helpNo = "ヘルプ No.3";
				helplabel.setText("『やりなおし』を押すとペナルティとして手数が増えます");
			}else if(hn == 3) {
				helpNo = "ヘルプ No.4";
				helplabel.setText("スイッチはレベルに応じて５種類まで増えます");
			}else if(hn == 4) {
				helpNo = "ヘルプ No.5";
				helplabel.setText("スコアはクリアするまでの手数が少ないほど多く貰えます");
			}else if(hn == 5) {
				helpNo = "ヘルプ No.6";
				helplabel.setText("レベルが20の倍数になると…？");
			}
			JOptionPane.showMessageDialog(this,helplabel,helpNo,JOptionPane.INFORMATION_MESSAGE);
/*リ*/	}else if (cmd.equals("btn_Reset")){
			con+=(lv/10)+1;
			label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
			label1.setText("スイッチをリセットします");
			RandomSet();
/*①*/	}else if (cmd.equals("btn_1")){
			con++;
			label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
			label1.setText("①が押されました");
			for(int i = 1; i <= 4;i*=2) {
				changeSwitch(i);
			}
			checkSwitch();
/*②*/	}else if (cmd.equals("btn_2")){
			con++;
			label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
			label1.setText("②が押されました");
			for(int i = 1; i <= 5;i++) {
				if(i != 4) {
					changeSwitch(i);
				}
			}
			checkSwitch();
/*③*/	}else if (cmd.equals("btn_3")){
			con++;
			label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
			label1.setText("③が押されました");
			for(int i = 2; i <= 6;i++) {
				if(i == 4)
					continue;
				if(i == 5)
					continue;
				changeSwitch(i);
			}
			checkSwitch();
/*④*/	}else if (cmd.equals("btn_4")){
			con++;
			label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
			label1.setText("④が押されました");
			for(int i = 1; i <= 7;i++) {
				if(i == 2)
					continue;
				if(i == 3)
					continue;
				if(i == 6)
					continue;
				changeSwitch(i);
			}
			checkSwitch();
/*⑤*/	}else if (cmd.equals("btn_5")){
			con++;
			label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
			label1.setText("⑤が押されました");
			for(int i = 2; i <= 8;i++) {
				if(i == 3)
					continue;
				if(i == 7)
					continue;
				changeSwitch(i);
			}
			checkSwitch();
/*⑥*/	}else if (cmd.equals("btn_6")){
			con++;
			label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
			label1.setText("⑥が押されました");
			for(int i = 3; i <= 9;i++) {
				if(i == 4)
					continue;
				if(i == 7)
					continue;
				if(i == 8)
					continue;
				changeSwitch(i);
			}
			checkSwitch();
/*⑦*/	}else if (cmd.equals("btn_7")){
			con++;
			label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
			label1.setText("⑦が押されました");
			for(int i = 4; i <= 8;i++) {
				if(i == 5)
					continue;
				if(i == 6)
					continue;
				changeSwitch(i);
			}
			checkSwitch();
/*⑧*/	}else if (cmd.equals("btn_8")){
			con++;
			label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
			label1.setText("⑧が押されました");
			for(int i = 5; i <= 9;i++) {
				if(i == 6)
					continue;
				changeSwitch(i);
			}
			checkSwitch();
/*⑨*/	}else if (cmd.equals("btn_9")){
			con++;
			label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
			label1.setText("⑨が押されました");
			for(int i = 6; i <= 9;i++) {
				if(i == 7)
					continue;
				changeSwitch(i);
			}
			checkSwitch();
		}
	  }

//スイッチのリセット処理
	void RandomSet() {
		//ランダム処理にメルセンヌ・ツイスタを使用
		int[] init_key = {(int) System.currentTimeMillis(), (int) Runtime.getRuntime().freeMemory()};
		Sfmt rnd = new Sfmt(init_key);
		int i = 0;
		for(JButton b:btn) {
			if(i != 0) {
				int j=0;
				if(lv > 15) {
					j = rnd.NextInt(5);
				}else if(lv > 9) {
					j = rnd.NextInt(4);
				}else if(lv > 3) {
					j = rnd.NextInt(3);
				}else{
					j = rnd.NextInt(2);
				}
				btn[i].setIcon(icon[j]);
			}
			i++;
		}

	}

//スイッチの変更処理
	void changeSwitch(int i) {
		if(lv > 15) {
			//5 ○,●,◎,◇,◆
			if(btn[i].getIcon().equals(icon[0])) {
				btn[i].setIcon(icon[1]);
			}else if(btn[i].getIcon().equals(icon[1])) {
				btn[i].setIcon(icon[2]);
			}else if(btn[i].getIcon().equals(icon[2])) {
				btn[i].setIcon(icon[3]);
			}else if(btn[i].getIcon().equals(icon[3])) {
				btn[i].setIcon(icon[4]);
			}else {
				btn[i].setIcon(icon[0]);
			}
		}else if(lv > 9) {
			//4 ○,●,◎,◇
			if(btn[i].getIcon().equals(icon[0])) {
				btn[i].setIcon(icon[1]);
			}else if(btn[i].getIcon().equals(icon[1])) {
				btn[i].setIcon(icon[2]);
			}else if(btn[i].getIcon().equals(icon[2])) {
				btn[i].setIcon(icon[3]);
			}else {
				btn[i].setIcon(icon[0]);
			}
		}else if(lv > 3) {
			//3 ○,●,◎
			if(btn[i].getIcon().equals(icon[0])) {
				btn[i].setIcon(icon[1]);
			}else if(btn[i].getIcon().equals(icon[1])) {
				btn[i].setIcon(icon[2]);
			}else {
				btn[i].setIcon(icon[0]);
			}
		}else{
			//2 ○,●
			if(btn[i].getIcon().equals(icon[0])) {
				btn[i].setIcon(icon[1]);
			}else {
				btn[i].setIcon(icon[0]);
			}
		}
	}

//クリア条件の判別
	void checkSwitch(){
		if(btn[9].getIcon().equals(btn[1].getIcon())){
			if(btn[9].getIcon().equals(btn[8].getIcon())){
				if(btn[8].getIcon().equals(btn[7].getIcon())){
					if(btn[7].getIcon().equals(btn[6].getIcon())){
						if(btn[6].getIcon().equals(btn[5].getIcon())){
							if(btn[5].getIcon().equals(btn[4].getIcon())){
								if(btn[4].getIcon().equals(btn[3].getIcon())){
									if(btn[3].getIcon().equals(btn[2].getIcon())){
										if(btn[2].getIcon().equals(btn[1].getIcon())){
											scr += (lv*100)/con;
											lv++;
											con = 0;
											label2.setText(String.format("LV %d SCORE %d 手数 %d",lv,scr,con));
											if(lv%20 != 0) {
												label1.setText("クリア!!");
											}else {	//レベルが20の倍数だった場合には別の文字を表示
												label1.setText("Congratulations!!");
											}
											RandomSet();
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
