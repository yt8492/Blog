package ui.page

import api.Api
import com.yt8492.blog.common.Constants
import com.yt8492.blog.common.model.Entry
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.display
import kotlinx.css.flexDirection
import react.*
import styled.css
import styled.styledDiv
import ui.component.entryRow

val entriesPage = functionalComponent<EntriesProps> { props ->
    val (state, setState) = useState(listOf<Entry>())
    useEffect(listOf(props.page)) {
        document.title = Constants.BLOG_TITLE
        MainScope().launch {
            val entries = Api.getPublicEntries(props.page)
            setState(entries)
        }
    }
    styledDiv {
        state.reversed().forEach { entry ->
            entryRow(entry)
        }

        css {
            display = Display.flex
            flexDirection = FlexDirection.column
        }
    }
}

external interface EntriesProps : RProps {
    var page: Int
}
