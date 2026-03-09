package explain.it.im10.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

val suggestionPool = listOf(
    "Why is the sky blue?",
    "What is gravity?",
    "How do airplanes fly?",
    "Why do stars shine?",
    "Why do we sleep?",
    "How does Wi-Fi work?",
    "Why is the ocean salty?",
    "What is electricity?",
    "Why do cats purr?",
    "What is a black hole?",
    "How does the internet work?",
    "Why do leaves change color?",
    "Why do we dream?",
    "How do vaccines work?",
    "Why do planets orbit the sun?",
    "Why do magnets attract?",
    "What is DNA?",
    "How does GPS work?",
    "Why does ice float?",
    "How do birds migrate?",
    "Why do we hiccup?",
    "Why do onions make us cry?",
    "How does a microwave work?",
    "Why is the moon sometimes visible during the day?",
    "Why do dogs wag their tails?",
    "How do rockets reach space?",
    "What makes thunder and lightning?",
    "Why do we blink?",
    "How does a camera take pictures?",
    "Why do bubbles pop?",
    "Why do we yawn?",
    "How do fish breathe underwater?",
    "Why do we have fingerprints?",
    "How do bees make honey?",
    "Why is fire hot?",
    "Why do we get goosebumps?",
    "What is time?",
    "How does a battery store energy?",
    "Why does the sun rise and set?",
    "Why is space dark?",
    "How do plants grow?",
    "Why do we get hungry?",
    "How does a refrigerator stay cold?",
    "Why does metal feel cold?",
    "Why do balloons float?",
    "What makes rainbows appear?",
    "How do submarines go underwater?",
    "Why do shadows change size?",
    "How do magnets work?",
    "Why do we laugh?",
    "How do spiders make webs?",
    "Why do camels store fat in humps?",
    "How do ants find food?",
    "Why do we sweat?",
    "How do touch screens work?",
    "Why do stars twinkle?",
    "How do electric cars work?",
    "Why do whales sing?",
    "How do chameleons change color?",
    "Why does water boil?",
    "How do glasses help us see?",
    "Why do we feel dizzy sometimes?",
    "How do elevators move up and down?",
    "Why do clouds float?",
    "How do satellites stay in orbit?",
    "Why do we have seasons?",
    "How do snowflakes form?",
    "Why does sugar dissolve in water?",
    "How do penguins stay warm?",
    "Why do we get cramps?",
    "How do headphones produce sound?",
    "Why do leaves fall in autumn?",
    "How does a compass work?",
    "Why does milk spoil?",
    "How do trains stay on tracks?",
    "Why do birds build nests?",
    "How does Bluetooth work?",
    "Why do people snore?",
    "How do solar panels make electricity?",
    "Why do we stretch when waking up?",
    "How do earthquakes happen?",
    "Why do frogs croak?",
    "How do engines power cars?",
    "Why do we feel ticklish?",
    "How do fireworks get their colors?",
    "Why do planets spin?",
    "How does a lock and key work?",
    "Why do cookies get hard when left out?",
    "How do wind turbines make energy?",
    "Why do mosquitoes bite?",
    "How do muscles move our body?",
    "Why do we have eyebrows?",
    "How do traffic lights work?",
    "Why do penguins walk funny?",
    "How do parachutes slow falling?",
    "Why does the moon change shape?",
    "How do glaciers move?",
    "Why do birds sing?",
    "How do robots work?"
)

@Composable
fun SuggestionsView(onClick: (String) -> Unit) {

    val suggestionPool = remember { suggestionPool } // your big list
    var suggestions by remember { mutableStateOf(suggestionPool.shuffled().take(10)) }

    val listState = rememberLazyListState()
    var isAutoScroll by remember { mutableStateOf(true) }

    LazyRow(
        state = listState, horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(suggestions) { suggestion ->
            SuggestionChip(suggestion) {
                isAutoScroll = false
                onClick(suggestion)
            }
        }
    }

    LaunchedEffect(isAutoScroll) {
        if (!isAutoScroll) return@LaunchedEffect
        while (true) {
            listState.animateScrollBy(2f)
            delay(16) // ~60fps
        }
    }
}


@Composable
fun SuggestionChip(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .background(Color.DarkGray)
            .padding(5.dp)
            .clickable { onClick() },
    ) {
        Text(text, color = White, fontSize = 12.sp)
    }
}