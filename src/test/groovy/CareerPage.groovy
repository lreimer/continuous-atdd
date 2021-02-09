import geb.Page

/**
 * The Geb page object for the Community page.
 */
class CareerPage extends Page {
    static url = "https://www.qaware.de/karriere/"
    static at = { browser.title.contains("Karriere") }
    static content = {
        headline { $("h1", 1) }
    }
}
