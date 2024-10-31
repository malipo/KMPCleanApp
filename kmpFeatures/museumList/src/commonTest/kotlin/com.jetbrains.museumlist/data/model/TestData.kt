package com.jetbrains.museumlist.data.model


object TestData {
    val fakeMuseumDTO = MuseumDTO(
        objectID = 1,
        title = "Mona Lisa",
        artistDisplayName = "Leonardo da Vinci",
        medium = "Oil on poplar",
        dimensions = "77 cm × 53 cm",
        objectURL = "https://example.com/mona-lisa",
        objectDate = "1503",
        primaryImage = "https://example.com/images/mona-lisa.jpg",
        primaryImageSmall = "https://example.com/images/mona-lisa-small.jpg",
        repository = "Louvre Museum",
        department = "Paintings",
        creditLine = "Gift of the Da Vinci Family"
    )
}