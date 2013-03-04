#DolphinJumpStart#

This project provides a step-by-step introduction to use OpenDolphin (http://open-dolphin.org)
- with plain Java (you can use any JVM language, particularly Groovy)
- with views in JavaFX (you can use any other Java Toolkit as well)
- with a simple Servlet (you can use any other Java Server technology as well: Grails, JEE6/7, Vert.x, etc.)

##Quick Setup##

Prerequisite: Java 7 or above.

If you want to use OpenDolphin in a Maven environment, please download

    dist/jumpstart-maven.zip

If you want to use OpenDolphin in a Gradle environment, please download

    dist/jumpstart-gradle.zip

Unzip to a location of you liking and you will see a jumpstart module/project structure
that we consider a best practice.

It contains the following modules/projects
- client: in a remote scenario, this is the client. It typically contains only view classes.
- server: in a remote scenario, this is the server. It typically contains controller actions.
- shared: this one is totally optional. If used, it typically contains shared constants between client and server.
- combined: combines all the above in one JVM for starting with the in-memory configuration for develop/test/debug.

##Running the samples##

Using Gradle you can call the following to start the tutorial application.

    ./gradlew run -Pstep=<step_id> // where step_id is between 0 and 7

- For the tutorial steps 0 to 4 there is a main class.

    ./combined/src/main/java/step_<stepId>/JumpStart.java

- For the steps 5,6 and 7 the static main part has been extracted into a starter class.

    ./combined/src/main/java/step_[5,6]/TutorialStarter.java
    ./client/src/main/java/step_7/TutorialStarter.java

Running the main method of the classes mentioned above you should see the tutorial application.

- When running the step_7 Tutorial make sure that the server application is running too.

To do so, call

    ./gradlew :server-app:runJetty

##The introduction steps##

We implement a very simple application that contains only one text field and two buttons to
'save' or 'reset' the value. 'Saving' will do nothing but printing the current field value
on the server side.

Both buttons are only enabled if there is really something to save/reset, i.e. the field value is dirty.
The dirty state is also visualized via a CSS class (background color changes).
Resetting triggers a 'shake' animation.

Steps 0 to 4 solely live in the "combined" module for a simple jumpstart before we properly
split client and server in step 5 and only keep a starter class in "combined".

Step 7 produces a war file that you can deploy on e.g. tomcat and the client starter moves to the "client" module.

- step 0 : Start a simple JavaFX view - only to validate that JavaFX is available.
- step 1 : Adding Nodes to stage, register onAction Handler.
- step 2 : Introducing a presentation model with one attribute and bind the value. Simple in-line setup in start method.
- step 3 : Logical separation between client and server.
- step 4 : Bind additional info like "dirty" of client attributes to the view.
- step 5 : Split into modules/projects. Use shared constants. Actions can no longer refer to any view (not on CP).
- step 6 : Shake on reset, better view factoring, let the "director" wire all application actions.
- step 7 : Remote setup. Generate self-contained war file. Move starter to client module.

##More Info##

This has only been a first glance into the way that OpenDolphin operates.

Many more features are available and you may want to check out the
user guide (http://open-dolphin.org/download/guide/index.html), the
other demos sources (http://github.com/canoo/open-dolphin/tree/master/subprojects/demo-javafx), or
the video demos (http://www.youtube.com/user/dierkkoenig).