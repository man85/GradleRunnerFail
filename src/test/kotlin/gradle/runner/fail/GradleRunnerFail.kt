package gradle.runner.fail

import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.CleanupMode
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Files

class GradleRunnerFail {

    companion object {
        private lateinit var projectInstanceFactory: ProjectInstanceFactory

        @JvmStatic
        @BeforeAll
        fun setup(@TempDir(cleanup = CleanupMode.ON_SUCCESS) tempDir: File) {
            projectInstanceFactory = ProjectInstanceFactory(tempDir)
        }
    }

    @Test
    fun testGradleRunnerFail() {
        val projectInstance = projectInstanceFactory.create(
            "project-with-reading-file-and-uri-in-settings"
        )
        val result = GradleRunnerFactory.createForIntegrationTest(projectInstance)
            .withArguments("build")
            .build()
        assertTrue(result.output.contains( "BUILD SUCCESSFUL"))
    }

}

internal class ProjectInstanceFactory(private val tempDir: File) {
    fun create(testProjectName: String): GradleProjectInstance {
        return GradleProjectInstance(
            Files.createTempDirectory(tempDir.toPath(), testProjectName).toFile(),
            testProjectName
        )
    }
}

object GradleRunnerFactory {
    fun createForIntegrationTest(projectInstance: GradleProjectInstance): GradleRunner {
        val runner = GradleRunner.create()
            .withProjectDir(projectInstance.rootDir)
            .withDebug(true)
            .withGradleInstallation(File(System.getenv("GRADLE_HOME")))
            .forwardOutput()

        return runner
    }
}

class GradleProjectInstance(
    val rootDir: File,
    exampleName: String,
) {

    init {
        val exampleDir = File(javaClass.classLoader.getResource(exampleName)!!.file)
        exampleDir.copyRecursively(rootDir)
    }
}