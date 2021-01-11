package ui.page

import api.Api
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.css.properties.borderTop
import react.RProps
import react.functionalComponent
import react.useEffect
import react.useState
import styled.css
import styled.styledArticle
import styled.styledDiv
import styled.styledFooter
import ui.component.*

val entryPage = functionalComponent<EntryProps> { props ->
    val id = EntryId(props.id)
    val (entry, setEntry) = useState<Entry?>(null)
    useEffect(listOf(props.id)) {
        MainScope().launch {
            Api.getEntryById(id)?.let {
                setEntry(it)
                document.title = """${it.title} - Log.d("yt8492", blog)"""
            }
        }
    }
    styledArticle {
        if (entry != null) {
            entryHeader(entry)
            markdown(entry.content)
        }
    }
    styledFooter {
        styledDiv {
            shareSection(entry)

            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                justifyContent = JustifyContent.center
            }
        }

        css {
            borderTop(1.px, BorderStyle.solid, Color.lightGray)
        }
    }
}

external interface EntryProps : RProps {
    var id: String
}
