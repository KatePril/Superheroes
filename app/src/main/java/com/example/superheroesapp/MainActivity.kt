package com.example.superheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.superheroesapp.datasource.HeroesRepository
import com.example.superheroesapp.model.Hero
import com.example.superheroesapp.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroApp()
                }
            }
        }
    }
}

@Composable
fun HeroIcon(
    @DrawableRes heroImg: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(heroImg),
        contentDescription = null,
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(start = dimensionResource(R.dimen.padding_medium))
            .clip(MaterialTheme.shapes.small),
//        contentScale = ContentScale.Crop
    )
}

@Composable
fun HeroInfo(
    @StringRes heroName: Int,
    @StringRes heroDescription: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(heroName),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(heroDescription),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            HeroInfo(
                heroName = hero.nameRes,
                heroDescription = hero.descriptionRes,
                modifier = Modifier.weight(2f)
            )
            HeroIcon(heroImg = hero.imageRes)
        }
    }
}

@Composable
fun TopBar() {
    Text(
        text = "Superheroes",
        style = MaterialTheme.typography.displayLarge,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeroApp() {
    Scaffold(
        topBar = {
            TopBar()
        }
    ) {
        it ->
        LazyColumn(contentPadding = it) {
            items(HeroesRepository.heroes) {
                HeroItem(
                    hero = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SuperheroesPreview() {
    SuperheroesTheme {
        HeroApp()
    }
}