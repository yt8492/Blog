package ui.page

import api.Api
import com.yt8492.blog.common.Constants
import com.yt8492.blog.common.model.Entry
import emotion.react.css
import io.ktor.http.*
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.*
import react.dom.html.ReactHTML.div
import tanstack.react.router.useLocation
import ui.component.entryRow
import web.cssom.*

private val compactEntriesMediaQuery = MediaQuery("(max-width: 640px)")

val entriesPage = FC<Props> {
    val location = useLocation()
    val page = parseQueryString(location.searchStr)["page"]?.toIntOrNull() ?: 1
    val (state, setState) = useState(listOf<Entry>())
    useEffect(page) {
        document.title = Constants.BLOG_TITLE
        MainScope().launch {
            val entries = Api.getPublicEntries(page)
            setState(entries)
        }
    }
    div {
        state.forEach { entry ->
            entryRow {
                this.entry = entry
            }
        }

        css {
            display = Display.flex
            flexDirection = FlexDirection.column

            Selector("> div")() {
                padding = Padding(24.px, 0.px, 20.px)
            }

            Selector("> div:first-of-type")() {
                paddingTop = 0.px
            }

            Selector("h1")() {
                lineHeight = 1.35.em
            }

            `@media`(compactEntriesMediaQuery) {
                fontSize = 18.px

                Selector("> div")() {
                    padding = Padding(24.px, 0.px, 22.px)
                }

                Selector("h1")() {
                    fontSize = 1.78.rem
                    lineHeight = 1.35.em
                }

                Selector("> div > div:first-of-type")() {
                    fontSize = 17.px
                }

                Selector("> div > div:nth-of-type(2)")() {
                    flexWrap = FlexWrap.wrap
                }

                Selector("> div > div:nth-of-type(2) > div")() {
                    fontSize = 14.px
                    marginBottom = 6.px
                    padding = Padding(4.px, 7.px)
                }

                Selector("> div > div:nth-of-type(3)")() {
                    fontSize = 18.px
                    lineHeight = 1.7.em
                    overflowWrap = OverflowWrap.anywhere
                    whiteSpace = WhiteSpace.normal
                }

                Selector("> div > div:nth-of-type(4)")() {
                    justifyContent = JustifyContent.stretch
                }

                Selector("> div > div:nth-of-type(4) a")() {
                    boxSizing = BoxSizing.borderBox
                    fontSize = 19.px
                    padding = Padding(13.px, 18.px)
                    textAlign = TextAlign.center
                    width = 100.pct
                }
            }
        }
    }
}
