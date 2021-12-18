use std::{fs, error::Error};
use std::slice::Iter;

pub fn start() {
    println!("Third ------------------------------");
    let input_values = fetch_input().expect("Something went wrong with the input values from the third day challenge.");
    part_one(&input_values);
    // part_two(&input_values);
}

fn fetch_input() -> Result<Vec<Vec<u32>>, Box<dyn Error>> {
    let input_values: Vec<Vec<u32>> = {
        let file_input: Vec<u8> = fs::read("files\\aoc_3.txt")?;
        let chunks: Vec<Vec<u8>> = file_input.chunks(14).map(|val| val[0..12].to_owned()).collect();
        chunks.into_iter().map(|chunk| chunk.into_iter().map(|u| (u as char).to_digit(10).unwrap()).collect()).collect()
    };

    Ok(input_values)
}

fn part_one(input_values: &Vec<Vec<u32>>) {
    let mut input_value_iters: Vec<Iter<u32>> = input_values.into_iter().map(|val| val.iter()).collect();
    let mut gamma: Vec<u32> = vec![];
    let mut epsilon: Vec<u32> = vec![];
    for _ in 0..input_value_iters[0].len() {
        let columns: Vec<&u32> = input_value_iters.iter_mut().map(|it| it.next().unwrap()).collect();
        let filtered_columns: Vec<&&u32> = columns.iter().filter(|col| **col > &0).collect();
        match (filtered_columns.len()) {
            0..=500 => {
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
        acc += (bit * exp);
        exp *= 2;
        acc
    })
}

// fn part_two(_input_values: &Vec<Vec<u32>>) {
// }