# Animal World

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.

## External Sources Used
- [LibGDX Button video](https://www.youtube.com/watch?v=ZLXruGWoUHU)
- [Button creation help courtesy of Stack Overflow user danielz](https://stackoverflow.com/questions/21488311/how-to-create-a-button-in-libgdx)
- [Button assets courtesy of GitHub user PhilipModDev](https://github.com/PhilipModDev/Libgdx-Tutorials/tree/main)
- [Sound effects for menu](https://www.youtube.com/watch?v=wgzBEOWlV88)
- [JFileChooser documentation used in AddAnimal command](https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html)
- [State Pattern help](https://www.digitalocean.com/community/tutorials/state-design-pattern-java)
- [LibGDX Official Documentation](https://libgdx.com/wiki/)
- [Button label change help courtesy of Stack Overflow user Tenfour04](https://stackoverflow.com/questions/29986914/change-text-size-of-textbutton-in-libgdx)
- [State pattern video](https://www.youtube.com/watch?v=abX4xzaAsoc&t=165s)
- [Command pattern video](https://www.youtube.com/watch?v=UfGD60BYzPM&t=216s)
- [Singleton video](https://www.youtube.com/watch?v=tSZn4wkBIu8&t=443s)
