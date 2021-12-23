use std::{fs, slice::Iter, error::Error};

const COLUMN_RAW_SIZE: usize = 14;
const COLUMN_SIZE: usize = 12;

pub fn start() {
    println!("Third ------------------------------");
    let input_values = fetch_input().expect("Something went wrong with the input values from the third day challenge.");
    let input_values_columnized: Vec<Vec<u32>> = convert_to_columns(&input_values);
    part_one(&input_values_columnized);
    part_two(input_values);
}

fn fetch_input() -> Result<Vec<Vec<u32>>, Box<dyn Error>> {
    let input_values: Vec<Vec<u32>> = {
        let file_input: Vec<u8> = fs::read("files\\aoc_3.txt")?;
        let chunks: Vec<Vec<u8>> = file_input.chunks(COLUMN_RAW_SIZE).map(|val| val[0..COLUMN_SIZE].to_owned()).collect();
        chunks.into_iter().map(|chunk| chunk.into_iter().map(|u| (u as char).to_digit(10).unwrap()).collect()).collect()
    };

    Ok(input_values)
}

fn convert_to_columns(input_values: &Vec<Vec<u32>>) -> Vec<Vec<u32>> {
    let mut columns: Vec<Vec<u32>> = vec![];
    let mut input_values_iter:Vec<Iter<u32>> = input_values.iter().map(|val| val.iter()).collect();
    for _ in 0..input_values_iter.first().unwrap().len() {
        let column: Vec<u32> = input_values_iter.iter_mut().map(|it| *it.next().unwrap()).collect();
        columns.push(column);
    }

    return columns;
}

fn part_one(columns: &Vec<Vec<u32>>) {
    let mut gamma: Vec<u32> = vec![];
    let mut epsilon: Vec<u32> = vec![];
    let columns_iter: Iter<Vec<u32>> = columns.iter();
    for column in columns_iter {
        let filtered_columns: usize = column.iter().filter(|val| **val == 0).collect::<Vec<&u32>>().len();
        match filtered_columns {
            0..=499 => {
                gamma.push(0);
                epsilon.push(1)
            }
            500..=1000 => {
                gamma.push(1);
                epsilon.push(0)
            }
            _ => {}
        };
    }

    let gamma_decimal: u32 = to_decimal(gamma);
    let epsilon_decimal: u32 = to_decimal(epsilon);
    let result: u32 = gamma_decimal * epsilon_decimal;
    println!("The result of day 3 part one is: {}", result);
}

fn to_decimal(bit_vector: Vec<u32>) -> u32 {
    let mut moved_bit_vector: Vec<u32> = bit_vector.to_owned();
    moved_bit_vector.reverse();
    let mut exp: u32 = 1;
    moved_bit_vector.iter().fold(0, |mut acc, bit| {
        acc += bit * exp;
        exp *= 2;
        acc
    })
}

fn part_two(input_values: Vec<Vec<u32>>) {
    let oxygen_generator_rating: Vec<u32> = reduce_ratings(input_values.to_owned(), String::from("majority"));
    let co2_scrubber_rating: Vec<u32> = reduce_ratings(input_values.to_owned(), String::from("minority"));

    let oxygen_generator_rating_decimal = to_decimal(oxygen_generator_rating);
    let co2_scrubber_rating_decimal = to_decimal(co2_scrubber_rating);
    let result = oxygen_generator_rating_decimal * co2_scrubber_rating_decimal;
    println!("The result of day 3 part one is: {}", result);
}

fn reduce_ratings(mut rating: Vec<Vec<u32>>, bit_type: String) -> Vec<u32> {
    for i in 0..COLUMN_SIZE {
        let mut zeros: Vec<u32> = vec![];
        let mut ones: Vec<u32> = vec![];
        for vec in &rating {
            let column_bit = vec.get(i).unwrap();
            match column_bit {
                0 => zeros.push(0),
                1 => ones.push(1),
                _ => {}
            }
        }

        let rating_bit = if ones.len() >= zeros.len() {
            if bit_type == "majority" {
                1
            } else {
                0
            }
        } else {
            if bit_type == "majority" {
                0
            } else {
                1
            }
        };

        let rating_length = rating.len();
        rating.retain(|vec| {
            if rating_length == 1 || vec.get(i).unwrap() == &rating_bit {
                return true;
            }
            false
        });
    }

    return rating.first().unwrap().to_owned();
}