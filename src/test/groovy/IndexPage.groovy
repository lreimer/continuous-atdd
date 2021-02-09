import geb.Page

/**
 * The Geb page object for the QAware index page.
 */
class IndexPage extends Page {
    static url = "https://www.qaware.de"
    static at = { browser.title.contains("QAware") }
    static content = {
        headline { $("h1", 0) }
    }
}
