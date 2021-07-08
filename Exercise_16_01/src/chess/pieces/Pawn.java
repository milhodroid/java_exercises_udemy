package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position position = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			position.setValues(this.position.getRow() - 1, this.position.getColumn());

			// check above position
			if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
				matrix[position.getRow()][position.getColumn()] = true;
			}
			position.setValues(this.position.getRow() - 2, this.position.getColumn());
			Position position2 = new Position(this.position.getRow() - 1, this.position.getColumn());

			// check two above position for first move
			if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)
					&& getBoard().positionExists(position2) && !getBoard().thereIsAPiece(position2)
					&& getMoveCount() == 0) {
				matrix[position.getRow()][position.getColumn()] = true;
			}

			// check northeast position
			position.setValues(this.position.getRow() - 1, this.position.getColumn() - 1);
			if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
				matrix[position.getRow()][position.getColumn()] = true;
			}

			// check northwest position
			position.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);
			if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
				matrix[position.getRow()][position.getColumn()] = true;
			}

			// #specialMove en passant - white pawns
			if (this.position.getRow() == 3) {
				Position left = new Position(this.position.getRow(), this.position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					matrix[left.getRow() - 1][left.getColumn()] = true;
				}
				Position right = new Position(this.position.getRow(), this.position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					matrix[right.getRow() - 1][right.getColumn()] = true;
				}
			}
		} else {
			position.setValues(this.position.getRow() + 1, this.position.getColumn());
			// check above position
			if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
				matrix[position.getRow()][position.getColumn()] = true;
			}
			position.setValues(this.position.getRow() + 2, this.position.getColumn());
			Position position2 = new Position(this.position.getRow() + 1, this.position.getColumn());

			// check two above position for first move
			if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)
					&& getBoard().positionExists(position2) && !getBoard().thereIsAPiece(position2)
					&& getMoveCount() == 0) {
				matrix[position.getRow()][position.getColumn()] = true;
			}

			// check northeast position
			position.setValues(this.position.getRow() + 1, this.position.getColumn() - 1);
			if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
				matrix[position.getRow()][position.getColumn()] = true;
			}

			// check northwest position
			position.setValues(this.position.getRow() + 1, this.position.getColumn() + 1);
			if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
				matrix[position.getRow()][position.getColumn()] = true;
			}

			// #specialMove en passant - black pawns
			if (this.position.getRow() == 4) {
				Position left = new Position(this.position.getRow(), this.position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					matrix[left.getRow() + 1][left.getColumn()] = true;
				}
				Position right = new Position(this.position.getRow(), this.position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					matrix[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}

		return matrix;

	}

	@Override
	public String toString() {
		return "P";
	}

}
