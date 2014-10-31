package com.michaelbaranov.microba.demo;

import com.michaelbaranov.microba.calendar.CalendarPane;
import com.michaelbaranov.microba.calendar.HolidayPolicy;
import com.michaelbaranov.microba.common.AbstractPolicy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.Calendar;
import java.util.Date;

public class Demo extends JApplet {

  public static void main(String[] s) {
    Demo demo = new Demo();
    demo.run();
  }

  private void run() {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(new BorderLayout());

    JTabbedPane tabs = new JTabbedPane();
    tabs.addTab("CalendarPane", buildCalendarPaneTab());

    frame.getContentPane().add(tabs, BorderLayout.CENTER);
    frame.setSize(500, 300);
    frame.setVisible(true);
  }

  public void start() {
    System.out.println("start");
    super.start();
  }

  public void stop() {
    System.out.println("stop");
    super.stop();
  }

  private class Hol extends AbstractPolicy implements HolidayPolicy {
    public String getHollidayName(Object source, Calendar date) {
      return null;
    }

    public boolean isHolliday(Object source, Calendar date) {
      return false;
    }

    public boolean isWeekend(Object source, Calendar date) {
      return date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }
  }

  private JComponent buildCalendarPaneTab() {
    JPanel panel = new JPanel();
    final CalendarPane calendarPane = new CalendarPane();

//		calendarPane.setEnabled(false);
    calendarPane.setHolidayPolicy(new Hol());

    try {
      calendarPane.setDate(new Date());
    } catch (PropertyVetoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    calendarPane.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        System.out.println("CalendarPane:" + calendarPane.getDate());

      }
    });

    panel.setLayout(new GridBagLayout());
    panel.add(calendarPane, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
            5, 5, 5, 5), 0, 0));

    return panel;

  }
}
