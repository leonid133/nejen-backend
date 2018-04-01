package nejen

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
class AppProperties {
    var app: App = App()
    var server: Server = Server()
    var database: Database = Database()

    class App {
        var name: String = ""
        var version: String = ""
    }

    class Database {
        var url: String = ""
        var driver: String = ""
        var username: String = ""
        var password: String = ""
    }

    class Server {
        var port: String = ""
    }
}