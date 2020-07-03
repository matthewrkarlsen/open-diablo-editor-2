package org.d1ablo.ode.main;

import org.d1ablo.ode.factories.DesktopFactory;
import org.d1ablo.ode.factories.ServerFactory;
import org.d1ablo.ode.gui.DesktopODE;
import org.d1ablo.ode.server.ODEServer;

import javax.swing.*;

/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */

/**
 * The core/main class of the program.
 */
public class ODEMain {

    public static void main(String[] args) {
        System.out.println("==================\nOpen Diablo Editor\n==================");
        if(args.length > 0) {
            runWithArg(args[0]);
        } else {
            runServer();
        }
    }

    private static void runServer() {
        System.out.println("No command line flags added, running ODE server (default action).");
        System.out.println("To re-run the program with the legacy user interface, use the " +
                "--legacy-graphical-ui option.");
        ODEServer odeServer = new ServerFactory().constructServer();
        odeServer.start();
    }

    private static void runWithArg(String firstArgument) {
        switch (firstArgument) {
            case "--legacy-graphical-ui":
                System.out.println("Loading legacy graphical user interface.");
                DesktopFactory desktopFactory = new DesktopFactory();
                DesktopODE desktopODE = desktopFactory.constructDesktopODE();
                SwingUtilities.invokeLater(desktopODE::displayGUI);
                break;
            case "--command-line-ui":
                System.err.println("Not yet implemented. Exiting.");
                System.exit(-1);
            case "--help":
                String text = "If you run this application without any options the application will perform the " +
                        "default action (running an ODE server, which enables you to access a GUI for modding via " +
                        "your browser). This server does not accept connections from other machines on your " +
                        "network. If you run the application with the --legacy-graphical-ui option, the old " +
                        "legacy GUI will be displayed (permitting editing without a browser). If you run the " +
                        "application with the --command-line-ui option a command line interface will be activated " +
                        "(to be implemented in a future version).";
                System.out.println(text);
                break;
            default:
                System.err.println("Option not recognised. Exiting.");
                System.exit(-1);
        }
    }


}
