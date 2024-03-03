package component;

import event.EventMenu;
import event.EventMenuCallBack;
import event.EventMenuSelected;
import model.Model_Menu;
import shadow.ShadowBorder;
import emas.PanelShadow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import travel.expense.calculator.Notes;
import travel.expense.calculator.Calculator;
import travel.expense.calculator.Advice;
import travel.expense.calculator.Calendar;


public class Menu extends PanelShadow {



    private int selectedIndex = -1;
    private double menuTarget;
    private double menuLastTarget;
    private double currentLocation;
    private BufferedImage selectedImage;
    private Animator animator;
    private EventMenuCallBack callBack;
    private EventMenu event;

    public Menu() {
        initComponents();
        init();
    }
     private void init() {
        setRadius(20);
        initData();
        listMenu.addEventSelectedMenu(new EventMenuSelected() {
            @Override
            public void menuSelected(int index, EventMenuCallBack callBack) {
                if (!animator.isRunning()) {
                    if (index != selectedIndex) {
                        Menu.this.callBack = callBack;                        
                        menuTarget = selectedIndex * 50 + listMenu.getY();
                        animator.start();                                      
                    }
                    
                    selectedIndex = index;
                switch (index) {
                            case 1:
                                
                                Calculator calculatorWindow = new Calculator();
                                calculatorWindow.setVisible(true);
                                break;
                            case 2:
                                
                                Notes notesWindow = new Notes();
                                notesWindow.setVisible(true);
                                break;
                            case 3:
                                
                                Calendar calendarWindow = new Calendar();
                                calendarWindow.setVisible(true);
                                break;
                            case 4:
                               
                                Advice adviceWindow = new Advice();
                                adviceWindow.setVisible(true);
                                break;                          
                                
                               
                    
                }
            }
        }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                currentLocation = (menuTarget - menuLastTarget) * fraction;
                currentLocation += menuLastTarget;
                
                repaint();
            }
            
             @Override
            public void end() {
                menuLastTarget = menuTarget;
                callBack.call(selectedIndex);
                if (event != null) {
                    event.menuIndexChange(selectedIndex);
                }
                selectedIndex=(int) ((currentLocation-listMenu.getY())/50);
            }
        };
        animator = new Animator(300, target);
        animator.setResolution(1);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
    }

    public void setSelectedIndex(int index) {
        selectedIndex = index;
        menuTarget = selectedIndex * 50 + listMenu.getY();
        menuLastTarget = menuTarget;
        currentLocation = menuLastTarget;
        listMenu.selectedIndex(index);
        repaint();
    }
    
    private void initData() {
        
        if (menuLastTarget == -1) {
        menuLastTarget = selectedIndex * 50 + listMenu.getY();}                
        listMenu.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
        listMenu.addItem(new Model_Menu("1", "Expense Calculator", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("2", "Destination Notes", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("3", "Calendar", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("4", "Travel advices", Model_Menu.MenuType.MENU));                
        listMenu.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
    }

    private void createImage() {
        int width = getWidth() - 30;
        selectedImage = ShadowBorder.getInstance().createShadowOut(width, 50, 8, 8, new Color(242, 246, 253));
    }

    @Override
    public void setBounds(int i, int i1, int i2, int i3) {
        super.setBounds(i, i1, i2, i3);
        createImage();
    }
    
     @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        if (selectedIndex >= 0) {
            grphcs.drawImage(selectedImage, 15, (int) currentLocation, null);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new emas.PanelShadow();
        profile1 = new component.Profile();
        listMenu = new emas.ListMenu<>();

        listMenu.setOpaque(false);

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(listMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

 public void addEvent(EventMenu event) {
        this.event = event;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private emas.ListMenu<String> listMenu;
    private emas.PanelShadow panelShadow1;
    private component.Profile profile1;
    // End of variables declaration//GEN-END:variables
}


