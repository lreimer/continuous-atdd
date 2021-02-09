import geb.Page

/**
 * The Geb page object for the Community page.
 */
class CommunityPage extends Page {
    static url = "https://www.qaware.de/wissen/"
    static at = { browser.title.contains("Wissen") }
    static content = {
        headline { $("h1", 1) }
    }
}
