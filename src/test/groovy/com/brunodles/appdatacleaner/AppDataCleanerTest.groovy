package com.brunodles.appdatacleaner

import com.android.build.gradle.AppPlugin
import com.brunodles.oleaster.suiterunner.OleasterSuiteRunner
import org.gradle.api.Project
import org.gradle.api.internal.plugins.PluginApplicationException
import org.gradle.testfixtures.ProjectBuilder
import org.junit.runner.RunWith

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe
import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@RunWith(OleasterSuiteRunner)
class AppDataCleanerTest {
    {
        describe("Given a project, when Apply AppDataCleaner plugin") {
            it("should fail") {
                Project project = ProjectBuilder.builder().build()
                assertThatThrownBy { project.pluginManager.apply(AppDataCleaner) }.
                        isInstanceOf(PluginApplicationException)
            }
            describe("when apply android plugin too") {
                it("should create a task to clean app variants") {
                    Project project = ProjectBuilder.builder().build()
                    project.pluginManager.apply(AppPlugin)
                    project.pluginManager.apply(AppDataCleaner)
                    assertThat(project.tasks.getByName("cleanAppDataAll")).isNotNull()
                }
            }
        }
    }
}
