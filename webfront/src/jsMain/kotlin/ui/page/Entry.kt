package ui.page

import api.Api
import com.yt8492.blog.common.Constants
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import emotion.react.css
import kotlinx.browser.document
import react.*
import react.dom.html.ReactHTML.article
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.footer
import tanstack.react.router.useLoaderData
import tanstack.router.core.LoaderFnContext
import tanstack.router.core.RouteLoaderFn
import ui.component.*
import web.cssom.*

private val compactEntryMediaQuery = MediaQuery("(max-width: 640px)")

val entryLoader = RouteLoaderFn { args: LoaderFnContext ->
    val rawId = args.params[ENTRY_ID_PARAM] ?: error("id not found")
    val id = EntryId(rawId)
    Api.getEntryById(id)
}

val entryPage = FC<Props> {
    val entry = useLoaderData { it as Entry? }
    useEffect(entry) {
        entry?.let {
            document.title = """${it.title} - ${Constants.BLOG_TITLE}"""
        }
    }
    article {
        if (entry != null) {
            entryHeader {
                this.entry = entry
            }
            markdown {
                src = entry.content
            }
        }

        css {
            boxSizing = BoxSizing.borderBox
            width = 100.pct
            overflowWrap = OverflowWrap.breakWord

            Selector("h1")() {
                lineHeight = 1.35.em
            }

            Selector("img")() {
                maxWidth = 100.pct
            }

            Selector("table")() {
                display = Display.block
                maxWidth = 100.pct
                overflowX = Auto.auto
            }

            Selector("pre")() {
                maxWidth = 100.pct
                overflowX = Auto.auto
            }

            Selector("code")() {
                overflowWrap = OverflowWrap.breakWord
            }

            Selector("> div:last-of-type")() {
                boxSizing = BoxSizing.borderBox
                maxWidth = 100.pct
            }

            `@media`(compactEntryMediaQuery) {
                fontSize = 18.px

                Selector("h1")() {
                    fontSize = 1.85.rem
                    lineHeight = 1.35.em
                    marginTop = 8.px
                }

                Selector("> div:first-of-type")() {
                    fontSize = 17.px
                }

                Selector("> div:nth-of-type(2)")() {
                    flexWrap = FlexWrap.wrap
                }

                Selector("> div:nth-of-type(2) > div")() {
                    fontSize = 14.px
                    marginBottom = 6.px
                    padding = Padding(4.px, 7.px)
                }

                Selector("p, li")() {
                    lineHeight = 1.75.em
                }

                Selector("> div:last-of-type")() {
                    padding = Padding(8.px, 0.px, 10.px)
                }

                Selector("table")() {
                    fontSize = 15.px
                }
            }
        }
    }
    footer {
        div {
            shareSection {
                this.entry = entry
            }

            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                justifyContent = JustifyContent.center
            }
        }

        css {
            borderTop = Border(1.px, LineStyle.solid, NamedColor.lightgray)
            marginTop = 32.px
            padding = Padding(0.px, 0.px, 24.px)

            `@media`(compactEntryMediaQuery) {
                marginTop = 24.px

                Selector("a")() {
                    height = 32.px
                    marginLeft = 12.px
                    marginRight = 12.px
                    padding = Padding(10.px, 10.px)
                    width = 32.px
                }
            }
        }
    }
}
