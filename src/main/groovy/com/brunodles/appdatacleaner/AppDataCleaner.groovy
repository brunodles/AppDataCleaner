package com.brunodles.appdatacleaner

import com.android.build.gradle.AppPlugin
import com.brunodles.auto.gradleplugin.AutoPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

@AutoPlugin("appdatacleaner")
class AppDataCleaner implements Plugin<Project> {

    @Override
    void apply(Project project) {
        if (!(project.plugins.hasPlugin(AppPlugin)))
            throw new IllegalStateException("Android plugin is required")

        final variants = project.android.applicationVariants
        variants.all { variant ->
            task("cleanAppData${variant.name.capitalize()}") {
                description "Clean app data on device for variant $variant.name"
                doLast {
                    "adb shell pm clear ${variant.applicationId}".execute().text.println()
                }
            }
        }

        project.task('cleanAppDataAll') {
            description "Clean app data on device for all variants"
            doLast {
                variants.all { variant ->
                    "adb shell pm clear ${variant.applicationId}".execute().text.println()
                }
            }
        }
    }
}
