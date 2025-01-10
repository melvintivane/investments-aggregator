package tech.melvin.agregadorinvestimentos.dto;

import java.util.List;

public record BrapiResponseDTO(List<StockDTO> results) {
}
