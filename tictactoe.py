import string


def print_field(f):
    print("---------")
    for i in f:
        print(f"| {i[0]} {i[1]} {i[2]} |")
    print("---------")


def check_state(f2):
    num_x = 0
    num_o = 0

    is_x_wins = False
    is_o_wins = False

    for i in range(len(f2)):
        for j in range(3):
            if f2[i][j] == 'X':
                num_x += 1
            elif f2[i][j] == 'O':
                num_o += 1

        if f2[i][0] == f2[i][1] == f2[i][2]:
            if f2[i][0] == 'X':
                is_x_wins = True
            elif f2[i][0] == 'O':
                is_o_wins = True

        if f2[0][i] == f2[1][i] == f2[2][i]:
            if f2[0][i] == 'X':
                is_x_wins = True
            elif f2[0][i] == 'O':
                is_o_wins = True

        # check 2 diagonals
        if f2[0][0] == f2[1][1] == f2[2][2] \
                or f2[0][2] == f2[1][1] == f2[2][0]:
            if f2[1][1] == 'X':
                is_x_wins = True
            elif f2[1][1] == 'O':
                is_o_wins = True

    if abs(num_x - num_o) > 1 or is_o_wins and is_x_wins:
        print("Impossible")
        return 0
    elif is_o_wins:
        print("O wins")
        return 0
    elif is_x_wins:
        print("X wins")
        return 0
    elif num_x + num_o == 9:
        print("Draw")
        return 0
    return 1


# s = input("Enter cells: ")
# s1 = []
# for i in range(len(s)):
#     if s[i] != 'X' and s[i] != 'O':
#         s1 += ' '
#     else:
#         s1 += s[i]

# field = [[s1[0], s1[1], s1[2]], [s1[3], s1[4], s1[5]], [s1[6], s1[7], s1[8]]]
field = [[' ' for _ in range(3)] for _ in range(3)]
print_field(field)
# check_state(field)

is_X_turn = True
while True:
    y, x = input("Enter the coordinates: ").split()
    if x not in string.digits or y not in string.digits:
        print("You should enter numbers!")
        continue
    y = int(y) - 1
    x = int(x) - 1
    if x not in range(3) or y not in range(3):
        print("Coordinates should be from 1 to 3!")
        continue
    if field[y][x] == 'X' or field[y][x] == 'O':
        print("This cell is occupied! Choose another one!")
    else:
        if is_X_turn:
            field[y][x] = 'X'
            is_X_turn = False
        else:
            field[y][x] = 'O'
            is_X_turn = True
        print_field(field)

    if not check_state(field):
        exit()
