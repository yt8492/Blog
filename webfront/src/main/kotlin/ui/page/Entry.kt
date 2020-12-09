package ui.page

import api.Api
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.RProps
import react.dom.div
import react.dom.h1
import react.functionalComponent
import react.useEffect
import react.useState
import ui.component.markdown

val entryPage = functionalComponent<EntryProps> { props ->
    val id = EntryId(props.id)
    val (state, setState) = useState<Entry?>(null)
    useEffect(listOf(props.id)) {
        MainScope().launch {
            val entry = Api.getEntryById(id)
            setState(entry)
        }
    }
    div {
        if (state != null) {
            h1 {
                + state.title
            }
            div {
                state.tags.forEach {
                    div {
                        + it
                    }
                }
            }
            markdown(state.content)
        }
    }
}

interface EntryProps : RProps {
    var id: String
}
