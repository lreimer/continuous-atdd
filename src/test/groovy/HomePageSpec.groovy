import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import spock.lang.Title

import static org.hamcrest.CoreMatchers.containsString
import static org.junit.Assert.assertThat

/**
 * A simple Spock specification to test the QAware homepage.
 */
@Title("Basic navigation features for QAware homepage.")
@Stepwise
class HomePageSpec extends GebReportingSpec {

    def "Launch browser and navigate to index page"() {
        when: 'we navigate to the QAware homepage'
        go("https://www.qaware.de")

        then: 'the index page is displayed'
        waitFor { at IndexPage }
        report "index page"

        and: 'the headline is correct'
        assertThat headline.text(), containsString("Qualität und Agilität")
    }

    def "Navigate to the career page"() {
        when: 'we navigate to the career page'
        to CareerPage

        then: 'the career page is displayed correctly'
        waitFor { at CareerPage }
        report "career page"
        assertThat headline.text(), containsString("ARBEITSPLATZ")
    }

    def "Navigate to the community page"() {
        when: 'we navigate to the community page'
        to CommunityPage

        then: 'the community page is displayed correctly'
        waitFor { at CommunityPage }
        report "community page"
        assertThat headline.text(), containsString("IT-Community")
    }
}