package ui.page

import api.Api
import com.yt8492.blog.common.Constants
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import js.promise.promise
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.css.*
import react.*
import react.router.useLoaderData
import remix.run.router.LoaderFunction
import styled.css
import styled.styledArticle
import styled.styledDiv
import styled.styledFooter
import ui.component.*

val entryLoader = LoaderFunction<dynamic> { arg ->
    val rawId = arg.params["id"] ?: error("id not found")
    console.log(rawId)
    val id = EntryId(rawId)
    MainScope().promise {
        val entry = Api.getEntryById(id)
        console.log(entry)
        entry
    }
}

val entryPage = fc<Props> {
    console.log("entry page")
    val entry = useLoaderData() as Entry?
    console.log(entry)
    useEffect(entry) {
        entry?.let {
            document.title = """${it.title} - ${Constants.BLOG_TITLE}"""
        }
    }
    styledArticle {
        if (entry != null) {
            entryHeader {
                attrs.entry = entry
            }
            markdown {
                attrs.src = entry.content
            }
        }
    }
    styledFooter {
        styledDiv {
            shareSection {
                attrs.entry = entry
            }

            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                justifyContent = JustifyContent.center
            }
        }

        css {
            borderTop = Border(1.px, BorderStyle.solid, Color.lightGray)
        }
    }
}
