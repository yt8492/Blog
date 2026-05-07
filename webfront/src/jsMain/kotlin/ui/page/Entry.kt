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
        }
    }
}
