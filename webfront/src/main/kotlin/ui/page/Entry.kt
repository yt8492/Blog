package ui.page

import api.Api
import com.yt8492.blog.common.Constants
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.css.*
import react.*
import react.router.useParams
import styled.css
import styled.styledArticle
import styled.styledDiv
import styled.styledFooter
import ui.component.*

val entryPage = fc<Props> {
    val params = useParams()
    val rawId = params["id"] ?: return@fc
    val id = EntryId(rawId)
    val (entry, setEntry) = useState<Entry?>(null)
    useEffectOnce {
        MainScope().launch {
            Api.getEntryById(id)?.let {
                setEntry(it)
                document.title = """${it.title} - ${Constants.BLOG_TITLE}"""
            }
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
