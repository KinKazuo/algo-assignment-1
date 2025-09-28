# Divide-and-Conquer Algorithms Assignment

## Implemented Algorithms

| Algorithm | Technique | Time Complexity | Recursion Depth |
|----------|-----------|------------------|------------------|
| MergeSort | D&C + buffer reuse + cutoff | Θ(n log n) | ⌈log₂ n⌉ |
| QuickSort | Random pivot + smaller-first recursion | Θ(n log n) avg | ≤ ~2·log₂ n |
| Deterministic Select | Median-of-Medians (group=5) | Θ(n) | O(log n) |
| Closest Pair (2D) | D&C + strip scan | Θ(n log n) | ⌈log₂ n⌉ |

---

## Recurrence Analysis

### 1. MergeSort
Recurrence:  
T(n) = 2T(n/2) + Θ(n)  
→ **Master Theorem Case 2** (a=2, b=2, f(n)=Θ(n) = Θ(n^log_b a))  
→ **T(n) = Θ(n log n)**  
Depth = ⌈log₂ n⌉ — confirmed by `Metrics`.

### 2. QuickSort (randomized)
Expected recurrence:  
T(n) = T(n/2) + T(n/2) + Θ(n) → but with **smaller-first recursion**, worst-case stack depth is bounded by **O(log n)**.  
Akra–Bazzi: p ≈ 1, ∫(1/u²) du converges → **T(n) = Θ(n log n)** expected.  
Measured depth ≲ 2·log₂ n — matches theory.

### 3. Deterministic Select (MoM5)
Recurrence:  
T(n) ≤ T(n/5) + T(7n/10) + Θ(n)  
→ Akra–Bazzi: solve (1/5)^p + (7/10)^p = 1 → p < 1  
→ **T(n) = Θ(n)**  
Constant factor is high (~20n comparisons), so slower than sort for n < 10⁴.

### 4. Closest Pair (2D)
Recurrence:  
T(n) = 2T(n/2) + Θ(n)  
→ **Master Theorem Case 2** → **T(n) = Θ(n log n)**  
Strip check is Θ(n) because only 7 neighbors are compared per point.

---

## Architecture Notes

- **Recursion depth** is explicitly tracked via `Metrics.enter()/exit()`.
- **MergeSort**: uses single reusable buffer → `allocations = 1`.
- **QuickSort**: iterates over larger partition → stack depth bounded.
- **Select**: recurses only into needed side → avoids O(n) stack.
- **CSV output** via CLI:  
  ```bash
