use std::{fs, error::Error};

pub fn start() {
    println!("First ------------------------------");
    let input_values = fetch_input().expect("Something is wrong with the input values...");
    part_one(&input_values);
    part_two(&input_values);
}

fn fetch_input() -> Result<Vec<i32>, Box<dyn Error>> {
    let file_input: Vec<u8> = fs::read("files\\aoc_1.txt")?;
    let mut buffer: Vec<u8> = Vec::new();
    let mut input_values: Vec<i32> = Vec::new();
    for character in file_input {
        if character == 44 {
            let char_coll: Vec<char> = buffer.iter().map(|c| c.to_owned() as char).collect();
            let stri: String = char_coll.iter().collect();
            input_values.push(stri.parse()?);
            buffer.clear();
            continue;
        }

        buffer.push(character);
    }

    Ok(input_values)
}

fn part_one(input_values: &Vec<i32>) {
    let amount = input_values.windows(2).filter(|val| val[0] < val[1]).count();
    println!("The amount of increasing items of part one is: {:?}", amount)
}

fn part_two(input_values: &Vec<i32>) {
    let mut window_sum_values: Vec<i32> = vec![];
    for x in input_values.windows(3) {
        window_sum_values.push(x[0] + x[1] + x[2])
    }

    println!("The amount of increasing items of part two is: {:?}", window_sum_values.windows(2).filter(|sums| sums[0] < sums[1]).count());
}