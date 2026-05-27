Project demonstrates that `Gradle 9.3.1` and `org.gradle.testkit.runner.GradleRunner` have different behavior: 
the `org.gradle.testkit.runner.GradleRunner` fails if there are file or uri functions in `settings.gradle.kts`.

To try it out, execute:
- `test-project-with-gradle.sh`
- `test-project-with-gradle-runner.sh`
