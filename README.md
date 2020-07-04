# Open Diablo Editor 2 #

Open Diablo Editor 2 is a program for making 'mods' for Diablo 1. It requires a full, legitimate, version of Diablo 1
to work correctly. The editor currently works with Diablo 1.09 (either the final original version or the GOG version).
Please note that if you are using the GOG exe, it will not work with the 'updated' exe provided
by GOG, only the original exe (as also provided by GOG).
  
## Running the Editor ##

- Obtain the correct jar file (at present, see 'Building via Maven' below).
- If you do not have a JVM installed, install Java SE 11 from [the official Oracle site](http://www.oracle.com/technetwork/java/javase/downloads/index.html).
- Place the jar file in the desired directory (should be empty apart from the jar). `C:\Program Files\ODE2` or similar is fine on Windows.
- Run the jar file with the command `java -jar open-diablo-editor-2.0.0b1.jar` (updating the version as appropriate).
  Alternatively, your operating system may simply let you double-click on the jar file to run it.
- navigate to [localhost:4666](http://localhost:4666/) in a recent version of Chrome or Firefox.
- Enjoy (hopefully).
- Send comments or raise issues as required.
- Be sure to shut down the editor using the final link on the editor page in your browser, or by pressing Ctrl+C in the
  console window that the application is running in.
  
## Important Usage Notes ##

- The editor currently requires a `Diablo.exe` file, v1.09 (the 'original' exe provided by GOG should work).
- Any Diablo.exe file that you load in to the editor will not be modified by loading the file or making edits in the editor.
- The program will create a copy of any exe file that you load, in your operating system's temp directory.
  This file will be modified.
- When you save your mod to disk, you specify a filename to save to. 
  Please do **NOT** specify your original `Diablo.exe` or you will save over the existing file
  and lose your 'reset' point.
  It is recommended to back up your original `Diablo.exe` file in case you accidentally do this.
    
## Building via Maven ##

- Install a Java 11 JDK from [the AdoptOpenJDK site](https://adoptopenjdk.net/) or your provider of choice.
- Set your `JAVA_HOME` variable.
- Add Java 11 to your `PATH` variable.
- Install Git from [the official Git site](https://git-scm.com/downloads).
- Bring up a command prompt for the folder that you want to download the code to.
    - In Windows, shift + right click in an explorer pane then select 'open prompt'.
- Run `git clone https://gitlab.com/d1ablo/open-diablo-editor-2.git`.
- Enter the `open-diablo-editor-2` folder and bring up a new prompt.
- Run `mvn package` (and wait for it to build and package the application).
- Move the resulting `open-diablo-editor-2.0.0b1-jar-with-dependencies.jar` file from the newly created `target` folder
  to a desired folder.
- Rename `open-diablo-editor-2.0.0b1-jar-with-dependencies.jar` to `open-diablo-editor-2.0.0b1.jar`.

## Contributors ##

- Matthew R. Karlsen
- Robin Eklind ('mewmew')
- 'ChaosMarc'

## Contributing ##

Please see CONTRIBUTING.md

## License ##

Please see LICENSE.md

## Acknowledgements ##

### Libraries Used ###

This project uses the following direct dependencies/libraries:

* The Undertow server (Apache 2.0 License)
* slf4j (MIT License)
* Apache Commons IO (Apache 2.0 License)
* Jackson (Apache 2.0 License)
* log4j 2 (Apache 2.0 License)
* Apache HTTP Components (Apache 2.0 License)
* JUnit (Eclipse Public License 1.0)
* Vue.js v2.6.11, (c) 2014-2019, by Evan You (MIT License)

Please consult the pom.xml file for details on the exact dependencies and versions used.

### Other Acknowledgements ###

The now-defunct mod workshop located at http://www.thedark5.com/info/mod.html was extremely valuable in the
construction of this software.

The workshop was originally created by Charlie with assistance from Jarulf.

A big thanks to Zamal & Zenda for continuing to host it while they could.

### Amendments ###

The project takes acknowledgements seriously. Should you believe that any acknowledgements
are awry, please raise an issue as soon as possible.