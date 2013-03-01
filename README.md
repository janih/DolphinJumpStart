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

##The introduction steps##

We implement a very simple application that contains only one text field and two buttons to
'save' or 'reset' the value. 'Saving' will do nothing else but printing the current field value
on the server side.

Both buttons are only enabled if there is really something to save/reset, i.e. the field value is dirty.
The dirty state is also visualized via a CSS class (background color changes).
Resetting triggers a 'shake' animation.

Steps 0 to 4 solely live in the "combined" module for a simple jumpstart before we properly
split client and server in step 5 and only keep a starter class in "combined".

Step 7 produces a @war@ file that you can deploy on e.g. tomcat and the client starter moves to the "client" module.
