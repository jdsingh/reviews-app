package me.jagdeep.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/hello") {
            call.respond("Hello World!")
        }

        // Serve the frontend react app
        singlePageApplication {
            staticFiles(
                "/",
                File("frontend/build/")
            )
        }
    }
}
