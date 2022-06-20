package com.example.cryptoobserverapp.models

data class Response(
    val lessons: List<Lesson>,
    val option: Option,
    val tabs: List<Tab>,
    val trainers: List<Trainer>
)