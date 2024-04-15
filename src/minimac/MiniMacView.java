package minimac;

import mvc.Subscriber;

import javax.swing.*;
import java.awt.*;

public class MiniMacView extends JPanel implements Subscriber {
    private MiniMacComponent macComponent;
    private MiniMac mac;

    public MiniMacView(MiniMac mac) {
        this.mac = mac;
        this.mac.subscribe(this);
        setLayout(new BorderLayout());
        macComponent = new MiniMacComponent(mac);
        add(macComponent, BorderLayout.CENTER);
    }


    public void setMiniMac(MiniMac newMac) {
        mac.unsubscribe(this);
        mac = newMac;
        mac.subscribe(this);
    }

    @Override
    public void update() {
        macComponent.updateLists(mac);
    }
}