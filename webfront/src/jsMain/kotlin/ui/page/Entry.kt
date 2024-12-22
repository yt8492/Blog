package ui.page

import api.Api
import com.yt8492.blog.common.Constants
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import emotion.react.css
import js.coroutines.promise
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import react.*
import react.dom.html.ReactHTML.article
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.footer
import react.router.useLoaderData
import remix.run.router.LoaderFunction
import ui.component.*
import web.cssom.*

val entryLoader = LoaderFunction<dynamic> { arg, _ ->
    val rawId = arg.params["id"] ?: error("id not found")
    console.log(rawId)
    val id = EntryId(rawId)
    MainScope().promise {
        val entry = Api.getEntryById(id)
        console.log(entry)
        entry
    }
}

val entryPage = FC<Props> {
    console.log("entry page")
    val entry = useLoaderData() as Entry?
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
