use std::io::{BufRead, BufReader};
use std::{fs::File, io};
use std::slice::Iter;

const BOARD_ROWS: i32 = 5;
const BOARD_COLUMNS: i32 = 5;

#[derive(Debug, Copy, Clone)]
struct Number {
    value: i32,
    highlighted: bool,
}

#[derive(Debug)]
struct Board {
    numbers: Vec<Vec<Number>>,
    consecutive: i32,
}

impl Board {
    fn new() -> Self {
        Board { numbers: vec![], consecutive: 0 }
    }

    fn highlight(&mut self, number: &i32) {
        for i in self.numbers.iter_mut() {
            for j in i.iter_mut() {
                if j.value == *number {
                    j.highlighted = true;
                }
            }
        }
    }

    fn check_rows(&mut self, last_number: &i32) -> Option<i32> {
        for row_vecs in &self.numbers {
            'inner: for row in row_vecs {
                if row.highlighted == true {
                    self.consecutive = self.consecutive + 1;
                } else {
                    self.consecutive = 0;
                    break 'inner;
                }

                if self.consecutive == 5 {
                    return Some(self.count_not_highlighted() * last_number);
                }
            }
        }

        None
    }

    fn check_columns(&mut self, last_number: &i32) -> Option<i32> {
        let numbers: &Vec<Vec<Number>> = &self.numbers.to_owned();
        let mut iters: Vec<Iter<Number>> = numbers.iter().map(|vec| vec.iter()).collect();
        for _ in 0..BOARD_COLUMNS {
            let columns: Vec<&Number> = iters.iter_mut().map(|iter| {
                iter.next().unwrap()
            }).collect();
            'inner: for column in columns {
                if column.highlighted == true {
                    self.consecutive = self.consecutive + 1;
                } else {
                    self.consecutive = 0;
                    break 'inner;
                }

                if self.consecutive == 5 {
                    return Some(self.count_not_highlighted() * last_number);
                }
            }
        }

        None
    }

    fn count_not_highlighted(&self) -> i32 {
        self.numbers.iter().fold(0, |acc, vec| vec.iter().fold(acc, |mut i_acc, &number| {
            if &number.highlighted == &false {
                i_acc = i_acc + number.value;
            }

            i_acc
        }))
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

    part_one(random_numbers, boards);
}

fn process_input(boards: &mut Vec<Board>) -> Result<Vec<i32>, io::Error> {
    let mut random_numbers: Vec<i32> = vec![];
    let mut recycle_board: Board = Board::new();
    let input_file: File = std::fs::File::open("files\\aoc_4.txt")?;
    let file_buffer = BufReader::new(input_file).lines();
    for (line_index, line) in file_buffer.enumerate() {
        match line {
            Ok(mut line) => {
                if line_index == 0 {
                    random_numbers = line
                        .split(',')
                        .map(|number| number.to_owned().parse::<i32>().unwrap())
                        .collect::<Vec<i32>>();
                } else {
                    if line == "" {
                        continue;
                    }

                    line = line.replace("  ", " ");

                    let board_numbers = line
                        .split(" ")
                        .map(|number| {
                            let value = match number.to_owned().parse::<i32>() {
                                Ok(value) => value,
                                Err(e) => -1,
                            };
                            Number { value, highlighted: false }
                        })
                        .filter(|number| number.value >= 0)
                        .collect::<Vec<Number>>();
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

fn part_one(random_numbers: Vec<i32>, mut boards: Vec<Board>) {
    'outer: for number in random_numbers.iter() {
        for board in boards.iter_mut() {
            board.highlight(number);
            if let Some(rows_result) = board.check_rows(&number) {
                println!("The result of day 3 part one is: {}", rows_result);
                break 'outer;
            } else if let Some(columns_result) = board.check_columns(&number) {
                println!("The result of day 3 part one is: {}", columns_result);
                break 'outer;
            }
        }
    }
}