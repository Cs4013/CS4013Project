package com.cs4013.Admin;

import com.cs4013.Misc.StringUtils;
import com.cs4013.Misc.TerminalLogger;

public class RoomManager {
    public int width = 48;

    public RoomManager() {
    }

    public void addRoom() {
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Centre | Add Room", 50, "|"));
        TerminalLogger.logln("+".repeat(width) + "\n");

        
    }

    public void editRoom() {
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Centre | Edit Room", 50, "|"));
        TerminalLogger.logln("+".repeat(width) + "\n");
    }

    public void deleteRoom() {
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Centre | Delete Room", 50, "|"));
        TerminalLogger.logln("+".repeat(width) + "\n");
    }

    public void viewRoom() {
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Centre | View Room", 50, "|"));
        TerminalLogger.logln("+".repeat(width) + "\n");
    }

}
