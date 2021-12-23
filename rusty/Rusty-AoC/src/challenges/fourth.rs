use std::io::{BufRead, BufReader};
use std::{fs::File, io};

const BOARD_ROWS: u32 = 5;
const BOARD_COLUMNS: u32 = 5;

#[derive(Debug)]
struct Board {
    numbers: Vec<Vec<u32>>,
}

impl Board {
    fn new() -> Self {
        Board { numbers: vec![] }
    }
}

impl Clone for Board {
    fn clone(&self) -> Self {
        let mut board = Board::new();
        let cloned_numbers = &self.numbers;
        board.numbers = cloned_numbers.to_owned();
        board
    }
}

pub fn start() {
    println!("Fourth ------------------------------");
    let mut boards: Vec<Board> = vec![];
    let random_numbers = match process_input(&mut boards) {
        Ok(random_numbers) => random_numbers,
        Err(e) => panic!("Unable to read from local input file: {}", e),
    };
}

fn process_input(boards: &mut Vec<Board>) -> Result<Vec<u32>, io::Error> {
    let mut random_numbers: Vec<u32> = vec![];
    let mut recycle_board: Board = Board::new();
    let input_file: File = std::fs::File::open("files\\aoc_4.txt")?;
    let file_buffer = BufReader::new(input_file).lines();
    for (line_index, line) in file_buffer.enumerate() {
        match line {
            Ok(line) => {
                if line_index == 0 {
                    random_numbers = line
                        .split(',')
                        .map(|number| number.to_owned().parse::<u32>().unwrap())
                        .collect::<Vec<u32>>();
                } else {
                    if line == "" {
                        continue;
                    }

                    let board_numbers = line
                        .split(' ')
                        .map(|number| number.to_owned().parse::<u32>().unwrap())
                        .collect::<Vec<u32>>();
                    recycle_board.numbers.push(board_numbers);

                    if recycle_board.numbers.len() == 5 {
                        // Skip empty lines and use them as indicator to start a new board.
                        boards.push(recycle_board.clone());
                        recycle_board.numbers = vec![];
                        continue;
                    }
                }
            }
            Err(e) => return Err(e),
        }
    }

    Ok(random_numbers)
}
